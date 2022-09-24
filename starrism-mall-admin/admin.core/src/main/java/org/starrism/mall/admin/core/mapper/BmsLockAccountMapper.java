package org.starrism.mall.admin.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.starrism.mall.admin.api.domain.dto.UnLockAccountDto;
import org.starrism.mall.admin.core.domain.entity.BmsLockAccount;
import org.starrism.mall.data.mapper.CoreMapper;

/**
 * <p>账户锁定记录表Mapper</p>
 *
 * @author hedwing
 * @since 2022/9/17
 **/
@Mapper
public interface BmsLockAccountMapper extends CoreMapper<BmsLockAccount> {
    /**
     * <p>查询指定用户是否被锁定</p>
     *
     * @param userId userId
     * @return org.starrism.mall.admin.core.domain.entity.BmsLockAccount
     * @author hedwing
     * @since 2022/9/18
     */
    BmsLockAccount findByUserId(@Param("userId") Long userId);

    /**
     * <p>新增用户锁定记录</p>
     *
     * @param bmsLockAccount bmsLockAccount
     * @author hedwing
     * @since 2022/9/18
     */
    void addLockAccountRecord(BmsLockAccount bmsLockAccount);

    /**
     * <p>解锁用户</p>
     *
     * @param dto dto
     * @author hedwing
     * @since 2022/9/24
     */
    void unlock(UnLockAccountDto dto);

}
