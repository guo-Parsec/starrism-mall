package org.starrism.mall.admin.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.starrism.mall.data.domain.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>账户锁定记录表</p>
 *
 * @author hedwing
 * @since 2022/9/17
 **/
@Setter
@Getter
@ToString
@TableName("bms_lock_account")
public class BmsLockAccount extends BaseEntity {
    private static final long serialVersionUID = -4052803079871070955L;

    /**
     * 账户id
     */
    private Long userId;

    /**
     * 锁定时间
     */
    private LocalDateTime lockTime;

    /**
     * 锁定状态
     */
    private Integer lockStatus;

    /**
     * 计划解锁时间
     */
    private LocalDateTime scheduledUnlockTime;

    /**
     * 实际解锁时间
     */
    private LocalDateTime actUnlockTime;

    /**
     * 锁定原因
     */
    private String lockReason;

    /**
     * 解锁原因
     */
    private String unlockReason;
}
