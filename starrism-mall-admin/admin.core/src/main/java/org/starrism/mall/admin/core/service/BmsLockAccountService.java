package org.starrism.mall.admin.core.service;

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
}
