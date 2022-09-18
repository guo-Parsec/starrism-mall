package org.starrism.mall.admin.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.base.domain.vo.CoreUser;
import org.starrism.mall.common.pools.AppPool;
import org.starrism.mall.common.pools.UrlPool;
import org.starrism.mall.common.rest.CommonResult;

import java.time.LocalDateTime;

/**
 * <p>用户对外服务接口</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@FeignClient(name = AppPool.APPLICATION_ADMIN_NAME, contextId = "bmsUserClient")
public interface BmsUserClient {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return org.lime.hedwing.base.rest.RestApi<org.lime.hedwing.api.system.pojo.vo.UserVo>
     * @author guochengqiang
     */
    @GetMapping(UrlPool.BMS_USER_PREFIX + "/find/{username}")
    CommonResult<CoreUser> findUserByUsername(@PathVariable String username);

    /**
     * <p>创建用户</p>
     *
     * @param userDto 用户
     * @return org.starrism.mall.common.rest.CommonResult<java.lang.Boolean>
     * @author hedwing
     * @since 2022/8/29
     */
    @PostMapping(value = UrlPool.BMS_USER_PREFIX + "/save")
    CommonResult<Boolean> saveUser(@RequestBody @Validated UserDto userDto);

    /**
     * <p>锁定用户</p>
     *
     * @param username            用户名
     * @param scheduledUnlockTime 计划解锁时间
     * @param lockTime            锁定时间
     * @param lockReason          解锁原因
     * @return org.starrism.mall.common.rest.CommonResult<java.lang.Void>
     * @author hedwing
     * @since 2022/9/18
     */
    @PostMapping(value = UrlPool.BMS_USER_PREFIX + "/lock")
    CommonResult<Void> lockUser(@RequestParam(value = "username") String username,
                                @RequestParam(value = "scheduledUnlockTime") LocalDateTime scheduledUnlockTime,
                                @RequestParam(value = "lockTime") LocalDateTime lockTime,
                                @RequestParam(value = "lockReason") String lockReason);

}
