package org.starrism.mall.admin.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.starrism.mall.admin.api.BmsUser;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Mapper
public interface BmsUserMapper {
    /**
     * <p>新增用户</p>
     *
     * @param bmsUser 系统用户
     * @author hedwing
     * @since 2022/8/13
     */
    void addUser(BmsUser bmsUser);
}
