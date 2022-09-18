package org.starrism.mall.admin.core.domain.converter;

import org.starrism.mall.admin.api.domain.vo.BmsLockAccountVo;
import org.starrism.mall.admin.core.domain.entity.BmsLockAccount;
import org.starrism.mall.common.domain.BaseConverters;
import org.starrism.mall.common.domain.Builder;
import org.starrism.mall.common.support.Convertible;

/**
 * <p>账户锁定记录转换类</p>
 *
 * @author hedwing
 * @since 2022/9/17
 **/
public class BmsLockAccountConverters implements BaseConverters {
    /**
     * <p>BmsLockAccount 转 BmsLockAccountVo 转换器</p>
     *
     * @param bmsLockAccount 账户锁定记录实体
     * @author hedwing
     * @since 2022/9/17
     */
    static Convertible<BmsLockAccount, BmsLockAccountVo> toLockAccountVoConverters(BmsLockAccount bmsLockAccount) {
        return (source) -> Builder.of(BmsLockAccountVo::new)
                .with(BmsLockAccountVo::setId, bmsLockAccount.getId())
                .with(BmsLockAccountVo::setUserId, bmsLockAccount.getUserId())
                .with(BmsLockAccountVo::setLockTime, bmsLockAccount.getLockTime())
                .with(BmsLockAccountVo::setLockStatus, bmsLockAccount.getLockStatus())
                .with(BmsLockAccountVo::setScheduledUnlockTime, bmsLockAccount.getScheduledUnlockTime())
                .with(BmsLockAccountVo::setActUnlockTime, bmsLockAccount.getActUnlockTime())
                .with(BmsLockAccountVo::setLockReason, bmsLockAccount.getLockReason())
                .with(BmsLockAccountVo::setUnlockReason, bmsLockAccount.getUnlockReason())
                .build();
    }

    /**
     * <p>BmsLockAccount 转 BmsLockAccountVo</p>
     *
     * @param bmsLockAccount bmsLockAccount
     * @return org.starrism.mall.admin.api.domain.vo.BmsLockAccountVo
     * @author hedwing
     * @since 2022/9/17
     */
    public static BmsLockAccountVo toLockAccountVo(BmsLockAccount bmsLockAccount) {
        return toLockAccountVoConverters(bmsLockAccount).convert(bmsLockAccount);
    }
}
