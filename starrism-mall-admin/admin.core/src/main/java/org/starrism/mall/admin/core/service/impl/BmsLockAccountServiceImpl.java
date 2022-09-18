package org.starrism.mall.admin.core.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.starrism.mall.admin.core.domain.entity.BmsLockAccount;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.admin.core.mapper.BmsLockAccountMapper;
import org.starrism.mall.admin.core.mapper.BmsUserMapper;
import org.starrism.mall.admin.core.rest.AdminResultCode;
import org.starrism.mall.admin.core.service.BmsLockAccountService;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.exceptions.StarrismException;
import org.starrism.mall.common.log.StarrismLogger;
import org.starrism.mall.common.log.StarrismLoggerFactory;
import org.starrism.mall.common.util.DateTimeUtil;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.data.pool.BasePool;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>锁定用户实现类</p>
 *
 * @author hedwing
 * @since 2022/9/18
 **/
@Service(value = "bmsLockAccountService")
public class BmsLockAccountServiceImpl implements BmsLockAccountService {
    private static final StarrismLogger LOGGER = StarrismLoggerFactory.getLogger(BmsLockAccountServiceImpl.class);
    @Resource
    BmsLockAccountMapper bmsLockAccountMapper;
    @Resource
    private BmsUserMapper bmsUserMapper;


    /**
     * <p>锁定用户</p>
     *
     * @param username            用户名
     * @param scheduledUnlockTime 计划解锁时间
     * @param lockTime            锁定时间
     * @param lockReason          解锁原因
     * @author hedwing
     * @since 2022/9/18
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void lockUser(String username, LocalDateTime scheduledUnlockTime, LocalDateTime lockTime, String lockReason) {
        BmsUser user = bmsUserMapper.findByUsername(username);
        if (BmsUser.isEmpty(user)) {
            LOGGER.error("用户[username={}]不存在", username);
            throw new StarrismException(AdminResultCode.USER_NOT_EXIST);
        }
        user.setEnableStatus(BasePool.LOCK_USER);
        bmsUserMapper.modifyUser(user);
        if (scheduledUnlockTime.isBefore(lockTime)) {
            LOGGER.error("计划解锁时间[{}]不能早于当前系统时间[{}]", DateTimeUtil.format(scheduledUnlockTime), DateTimeUtil.format(lockTime));
            throw new StarrismException("计划解锁时间不能早于当前系统时间", AdminResultCode.LOCK_USER_ERROR);
        }
        if (StrUtil.isBlank(lockReason)) {
            LOGGER.error("锁定原因不能为空");
            throw new StarrismException("锁定原因不能为空", AdminResultCode.LOCK_USER_ERROR);
        }
        BmsLockAccount lockAccount = Builder.of(BmsLockAccount::new)
                .with(BmsLockAccount::setUserId, user.getId())
                .with(BmsLockAccount::setLockTime, lockTime)
                .with(BmsLockAccount::setLockStatus, BasePool.LOCK_ACCOUNT)
                .with(BmsLockAccount::setScheduledUnlockTime, scheduledUnlockTime)
                .with(BmsLockAccount::setLockReason, lockReason)
                .build();
        bmsLockAccountMapper.insert(lockAccount);
    }
}
