package org.starrism.mall.admin.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.starrism.mall.common.pools.AppPool;
import org.starrism.mall.common.pools.UrlPool;
import org.starrism.mall.common.rest.CommonResult;

import java.util.Set;

/**
 * <p>角色对外服务接口</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
@FeignClient(name = AppPool.APPLICATION_ADMIN_NAME, contextId = "bmsRoleClient")
public interface BmsRoleClient {
    /**
     * <p>根据用户名查询角色</p>
     *
     * @param username 用户名
     * @return org.starrism.mall.common.rest.CommonResult<java.util.Set < java.lang.String>>
     * @author hedwing
     * @since 2022/8/29
     */
    @GetMapping(value = UrlPool.BMS_ROLE_PREFIX + "/find/role-code/by/username")
    public CommonResult<Set<String>> findRoleCodesByUsername(@RequestParam("username") String username);

    /**
     * <p>根据用户id查询角色</p>
     *
     * @param userId 用户id
     * @return org.starrism.mall.common.rest.CommonResult<java.util.Set < java.lang.String>>
     * @author hedwing
     * @since 2022/8/29
     */
    @GetMapping(value = UrlPool.BMS_ROLE_PREFIX + "/find/role-code/by/userId")
    public CommonResult<Set<String>> findRoleCodesByUserId(@RequestParam("userId") Long userId);
}
