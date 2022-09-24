package org.starrism.mall.admin.api.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>解锁用户参数</p>
 *
 * @author hedwing
 * @since 2022/9/24
 **/
@Setter
@Getter
public class UnLockAccountDto {
    @ApiModelProperty(value = "锁定用户记录主键")
    @NotNull(message = "锁定用户记录主键不能为空")
    private Long id;

    /**
     * 账户id
     */
    @ApiModelProperty(value = "账户id")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 锁定时间
     */
    @ApiModelProperty(value = "锁定时间")
    private LocalDateTime lockTime;

    /**
     * 锁定状态
     */
    @ApiModelProperty(value = "锁定状态")
    @NotNull(message = "锁定状态不能为空")
    private Integer lockStatus;

    /**
     * 计划解锁时间
     */
    @ApiModelProperty(value = "计划解锁时间")
    private LocalDateTime scheduledUnlockTime;

    /**
     * 实际解锁时间
     */
    @ApiModelProperty(value = "实际解锁时间")
    @NotNull(message = "实际解锁时间不能为空")
    private LocalDateTime actUnlockTime;

    /**
     * 锁定原因
     */
    @ApiModelProperty(value = "锁定原因")
    private String lockReason;

    /**
     * 解锁原因
     */
    @ApiModelProperty(value = "解锁原因")
    @NotNull(message = "解锁原因不能为空")
    private String unlockReason;
}
