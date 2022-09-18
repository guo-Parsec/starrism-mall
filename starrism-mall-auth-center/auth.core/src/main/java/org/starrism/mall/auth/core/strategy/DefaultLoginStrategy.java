package org.starrism.mall.auth.core.strategy;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starrism.mall.admin.api.domain.dto.UserLoginDto;
import org.starrism.mall.admin.api.feign.BmsUserClient;
import org.starrism.mall.auth.core.domain.vo.AuthInfoVo;
import org.starrism.mall.auth.core.exception.AuthException;
import org.starrism.mall.auth.core.pool.UserLoginPool;
import org.starrism.mall.auth.core.rest.AuthResultCode;
import org.starrism.mall.base.access.ParamAccess;
import org.starrism.mall.base.domain.vo.BmsParamVo;
import org.starrism.mall.base.domain.vo.CoreUser;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.domain.vo.AccessTokenVo;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;
import org.starrism.mall.common.pools.AuthPool;
import org.starrism.mall.common.pools.ParamPool;
import org.starrism.mall.common.rest.CommonResult;
import org.starrism.mall.common.service.RedisService;
import org.starrism.mall.common.support.CommonConverts;
import org.starrism.mall.common.util.CollectionUtil;
import org.starrism.mall.common.util.DateTimeUtil;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.common.util.TextFormat;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/9/18
 **/
@Service(value = UserLoginPool.DEFAULT_LOGIN_STRATEGY_NAME)
public class DefaultLoginStrategy implements LoginStrategy {
    private static final StarrismLogger LOGGER = StarrismLoggerFactory.getLogger(DefaultLoginStrategy.class);
    @Resource
    private BmsUserClient bmsUserClient;
    @Resource
    private ParamAccess paramAccess;

    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }


    /**
     * <p>登录人信息实体获取处理器</p>
     *
     * @param userLoginDto userLoginDto
     * @return CoreUser
     * @author hedwing
     * @since 2022/9/18
     */
    @Override
    public CoreUser loginPersonEntityAcquisitionProcessor(UserLoginDto userLoginDto) {
        LOGGER.debug("[defaultLoginStrategy]登录人信息实体获取处理器开始使用参数[userLoginDto={}]处理", userLoginDto);
        if (userLoginDto == null || StrUtil.isBlank(userLoginDto.getUsername())) {
            LOGGER.error("username cannot be empty");
            throw new AuthException(AuthResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        String username = userLoginDto.getUsername();
        CommonResult<CoreUser> clientApi = bmsUserClient.findUserByUsername(username);
        CoreUser coreUser = CommonResult.getSuccessData(clientApi);
        if (coreUser == null) {
            LOGGER.error("cannot find user of username={}", username);
            throw new AuthException(AuthResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        return coreUser;
    }

    /**
     * <p>密码匹配错误处理器</p>
     *
     * @param coreUser coreUser
     * @author hedwing
     * @since 2022/9/18
     */
    @Override
    public void passwordMatchErrorProcessor(CoreUser coreUser) {
        LOGGER.debug("[defaultLoginStrategy]密码匹配错误处理器开始使用参数[coreUser={}]处理", coreUser);
        Map<String, String> pwdLockUserParamMap = findPwdLockUserParamMap();
        if (CollectionUtil.isEmpty(pwdLockUserParamMap)) {
            return;
        }
        String now = DateTimeUtil.now();
        LocalDateTime nowDt = DateTimeUtil.parse(now);
        String redisPwdWrongCountKey = TextFormat.format(UserLoginPool.USER_LOGIN_PWD_WRONG_COUNT_REDIS_KEY, coreUser.getId());
        String redisPwdWrongTimeKey = TextFormat.format(UserLoginPool.USER_LOGIN_PWD_WRONG_TIME_REDIS_KEY, coreUser.getId());
        boolean hasWrongRecord = redisService.hasKey(redisPwdWrongCountKey) || redisService.hasKey(redisPwdWrongTimeKey);
        // 在指定小时数内输错密码指定次数后将会被锁定
        int lockLimitTime = CommonConverts.strToInt().convert(pwdLockUserParamMap.get(ParamPool.PWD_WRONG_LOCK_HOURS_KEY), ParamPool.DEFAULT_PWD_WRONG_LOCK_HOURS);
        Integer pwdWrongCount = CommonConverts.toInt().convert(redisService.get(redisPwdWrongCountKey), 0);
        String pwdWrongTime = CommonConverts.toStr().convert(redisService.get(redisPwdWrongTimeKey), DateTimeUtil.now());
        // 如果为第一次输错密码则记录错误时间和信息
        if (!hasWrongRecord) {
            redisService.setByHour(redisPwdWrongCountKey, 1, lockLimitTime);
            redisService.setByHour(redisPwdWrongTimeKey, now, lockLimitTime);
            return;
        }
        // 如果已记录其密码错误信息则判断错误次数是否超过指定次数
        Integer maxTolerancePwdWrongCount = CommonConverts.strToInt().convert(pwdLockUserParamMap.get(ParamPool.MAX_TOLERANCE_PWD_WRONG_KEY), ParamPool.DEFAULT_MAX_TOLERANCE_PWD_WRONG_COUNT);
        Integer scheduledUnlockHours = CommonConverts.strToInt().convert(pwdLockUserParamMap.get(ParamPool.PWD_WRONG_UNLOCK_HOURS_KEY), ParamPool.DEFAULT_PWD_WRONG_UNLOCK_HOURS);
        // 如果超过指定次数并且仍在输错密码错误期限内则锁定账户
        if (pwdWrongCount >= maxTolerancePwdWrongCount && DateTimeUtil.between(now, pwdWrongTime, ChronoUnit.HOURS) <= lockLimitTime) {
            String lockReason = TextFormat.format(UserLoginPool.USER_LOGIN_PWD_WRONG_LOCK_REASON, lockLimitTime, maxTolerancePwdWrongCount);
            LocalDateTime scheduledUnlockTime = nowDt.plusHours(scheduledUnlockHours);
            CommonResult<Void> result = bmsUserClient.lockUser(coreUser.getUsername(), scheduledUnlockTime, nowDt, lockReason);
            CommonResult.isSuccess(result);
            return;
        }
        redisService.incr(redisPwdWrongCountKey, 1);
    }

    /**
     * <p>登录成功处理器</p>
     *
     * @param coreUser coreUser
     * @return org.starrism.mall.auth.core.domain.vo.AuthInfoVo
     * @author hedwing
     * @since 2022/9/18
     */
    @Override
    public AuthInfoVo loginSuccessProcessor(CoreUser coreUser) {
        LOGGER.debug("[defaultLoginStrategy]密码匹配错误处理器开始使用参数[coreUser={}]处理", coreUser);
        SaTokenInfo saTokenInfo = null;
        // 密码校验成功后登录，一行代码实现登录
        StpUtil.login(coreUser.getId());
        // 将用户信息存储到Session中
        // 密码校验完成后清空密码
        coreUser.setPassword(null);
        StpUtil.getSession().set(AuthPool.USER_SESSION, coreUser);
        // 获取当前登录用户Token信息
        saTokenInfo = StpUtil.getTokenInfo();
        AccessTokenVo accessToken = Builder.of(AccessTokenVo::new)
                .with(AccessTokenVo::setAccessToken, saTokenInfo.getTokenValue())
                .with(AccessTokenVo::setAccessTokenName, saTokenInfo.getTokenName())
                .build();
        return Builder.of(AuthInfoVo::new)
                .with(AuthInfoVo::setCoreUser, coreUser)
                .with(AuthInfoVo::setAccessToken, accessToken)
                .build();
    }

    /**
     * <p>查询密码错误超过设定后锁定用户参数组</p>
     * 返回结果map key为paramCode value为paramValue
     *
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @author hedwing
     * @since 2022/9/17
     */
    private Map<String, String> findPwdLockUserParamMap() {
        List<BmsParamVo> paramList = paramAccess.findByGroupCode(ParamPool.PWD_LOCK_USER_GROUP_KEY, StrUtil.EMPTY);
        int minSize = Integer.min(paramList.size(), 1);
        Map<String, String> pwdLockUserParamMap = Maps.newHashMapWithExpectedSize(minSize);
        if (CollectionUtil.isNotEmpty(paramList)) {
            pwdLockUserParamMap = paramList.stream().collect(Collectors.toMap(BmsParamVo::getParamCode, BmsParamVo::getParamValue, (k1, k2) -> k1));
            String pwdLockUserSwitchParamValue = pwdLockUserParamMap.get(ParamPool.PWD_WRONG_LOCK_SWITCH_KEY);
            if (StrUtil.isBlank(pwdLockUserSwitchParamValue) || !ParamPool.PWD_WRONG_LOCK_SWITCH_ON.equals(pwdLockUserSwitchParamValue)) {
                LOGGER.debug("密码输错锁定开关未开启");
                return Maps.newHashMapWithExpectedSize(minSize);
            }
            LOGGER.debug("密码输错锁定开关已开启");
        }
        return pwdLockUserParamMap;
    }
}
