package org.starrism.mall.admin.api.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.base.context.DictContext;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.common.pools.DictPool;

import java.time.LocalDateTime;

/**
 * <p>账户锁定记录展示层</p>
 *
 * @author hedwing
 * @since 2022/9/17
 **/
@Setter
@Getter
public class BmsLockAccountVo implements Domainizable {
    private static final long serialVersionUID = 6051871827165385601L;

    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 账户id
     */
    @ApiModelProperty(value = "账户id")
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
    private Integer lockStatus;

    /**
     * 锁定状态名称
     */
    @ApiModelProperty(value = "锁定状态名称")
    private String lockStatusName;

    /**
     * 计划解锁时间
     */
    @ApiModelProperty(value = "计划解锁时间")
    private LocalDateTime scheduledUnlockTime;

    /**
     * 实际解锁时间
     */
    @ApiModelProperty(value = "实际解锁时间")
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
    private String unlockReason;

    public String getLockStatusName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_ACCOUNT_LOCK_STATUS, this.lockStatus);
    }
}
