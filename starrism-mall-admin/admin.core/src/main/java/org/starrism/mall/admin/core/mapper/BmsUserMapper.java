package org.starrism.mall.admin.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.starrism.mall.admin.core.domain.entity.BmsUser;
import org.starrism.mall.data.mapper.CoreMapper;

import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Mapper
public interface BmsUserMapper extends CoreMapper<BmsUser> {
    /**
     * <p>根据主键获取数据</p>
     *
     * @param id 主键
     * @return org.starrism.mall.admin.api.domain.entity.BmsUser 认证用户
     * @author hedwing
     * @since 2022/8/13
     */
    BmsUser findById(Long id);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    BmsUser findByUsername(@Param("username") String username);

    /**
     * <p>新增用户</p>
     *
     * @param bmsUser 系统用户
     * @author hedwing
     * @since 2022/8/13
     */
    void addUser(BmsUser bmsUser);

    /**
     * <p>为用户赋予角色</p>
     *
     * @param userId  用户id
     * @param roleIds 角色id列表
     * @author hedwing
     * @since 2022/8/29
     */
    void grantRoleToUser(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);
}
