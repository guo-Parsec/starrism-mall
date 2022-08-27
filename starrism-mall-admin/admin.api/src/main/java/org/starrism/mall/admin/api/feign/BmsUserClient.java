package org.starrism.mall.admin.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.starrism.mall.common.domain.vo.AuthUser;
import org.starrism.mall.common.pools.AppPool;
import org.starrism.mall.common.rest.CommonResult;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@FeignClient(name = AppPool.APPLICATION_ADMIN_NAME + "/admin")
public interface BmsUserClient {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return org.lime.hedwing.base.rest.RestApi<org.lime.hedwing.api.system.pojo.vo.UserVo>
     * @author guochengqiang

     */
    @GetMapping("/v1/bms/user/find/{username}")
    CommonResult<AuthUser> findUserByUsername(@PathVariable String username);

}
