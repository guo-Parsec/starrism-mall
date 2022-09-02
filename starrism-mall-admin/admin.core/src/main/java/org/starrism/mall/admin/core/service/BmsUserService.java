package org.starrism.mall.admin.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.admin.api.domain.dto.UserPageDto;
import org.starrism.mall.admin.api.domain.vo.BmsUserVo;
import org.starrism.mall.base.domain.vo.CoreUser;

import java.util.Set;

/**
 * <p>系统用户业务接口</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public interface BmsUserService {
    /**
     * <p>分页查询用户数据</p>
     * @param userPageDto 用户dto
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<org.starrism.mall.admin.api.domain.vo.BmsUserVo>
     * @author hedwing
     * @since 2022/9/2
     */
    Page<BmsUserVo> page(UserPageDto userPageDto);

    /**
     * <p>根据用户名查询用户信息</p>
     *
     * @param username 用户名
     * @return org.starrism.mall.data.domain.vo.CoreUser
     * @author hedwing
     * @since 2022/8/27
     */
    CoreUser findUserByUsername(String username);

    /**
     * 保存用户
     *
     * @param userDto 用户
     * @return 是否保存成功
     */
    boolean saveUser(UserDto userDto);

    /**
     * <p>为用户赋予角色</p>
     *
     * @param userId      用户id
     * @param roleCodeSet 角色code列表
     * @return boolean
     * @author hedwing
     * @since 2022/8/31
     */
    boolean grantRoleToUser(Long userId, Set<String> roleCodeSet);

    /**
     * <p>禁用用户</p>
     *
     * @param userId 用户id
     * @return boolean
     * @author hedwing
     * @since 2022/9/2
     */
    boolean disabledUser(Long userId);

    /**
     * <p>启用用户</p>
     *
     * @param userId 用户id
     * @return boolean
     * @author hedwing
     * @since 2022/9/2
     */
    boolean enableUser(Long userId);

    /**
     * <p>逻辑删除用户</p>
     *
     * @param userId 用户id
     * @return boolean
     * @author hedwing
     * @since 2022/9/2
     */
    boolean logicRemoveUser(Long userId);

    /**
     * <p>物理删除用户</p>
     *
     * @param userId 用户id
     * @return boolean
     * @author hedwing
     * @since 2022/9/2
     */
    boolean physicalRemoveUser(Long userId);
}
