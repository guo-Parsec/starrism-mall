package org.starrism.mall.admin.core.service;

import org.starrism.mall.admin.api.domain.dto.UnLockAccountDto;
import org.starrism.mall.admin.api.domain.vo.BmsLockAccountVo;

import java.time.LocalDateTime;

/**
 * <p>锁定用户接口</p>
 *
 * @author hedwing
 * @since 2022/9/18
 **/
public interface BmsLockAccountService {
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
    void lockUser(String username, LocalDateTime scheduledUnlockTime, LocalDateTime lockTime, String lockReason);

    /**
     * <p>通过用户id查询用户锁定信息</p>
     *
     * @param userId userId
     * @return org.starrism.mall.admin.api.domain.vo.BmsLockAccountVo
     * @author hedwing
     * @since 2022/9/24
     */
    BmsLockAccountVo findLockUserInfoByUserId(Long userId);

    /**
     * <p>解锁用户</p>
     *
     * @param dto dto
     * @author hedwing
     * @since 2022/9/24
     */
    void unlockUser(UnLockAccountDto dto);
}
