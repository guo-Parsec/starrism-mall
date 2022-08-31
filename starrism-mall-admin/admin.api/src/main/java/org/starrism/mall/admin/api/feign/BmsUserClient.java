package org.starrism.mall.admin.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.starrism.mall.admin.api.domain.dto.UserDto;
import org.starrism.mall.base.domain.vo.CoreUser;
import org.starrism.mall.common.pools.AppPool;
import org.starrism.mall.common.rest.CommonResult;

/**
 * <p>用户对外服务接口</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@FeignClient(name = AppPool.APPLICATION_ADMIN_NAME, contextId = "bmsUserClient")
public interface BmsUserClient {
    String URL_PREFIX = "/v1/bms/user";

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return org.lime.hedwing.base.rest.RestApi<org.lime.hedwing.api.system.pojo.vo.UserVo>
     * @author guochengqiang
     */
    @GetMapping(URL_PREFIX + "/find/{username}")
    CommonResult<CoreUser> findUserByUsername(@PathVariable String username);

    /**
     * <p>创建用户</p>
     *
     * @param userDto 用户
     * @return org.starrism.mall.common.rest.CommonResult<java.lang.Boolean>
     * @author hedwing
     * @since 2022/8/29
     */
    @PostMapping(value = URL_PREFIX + "/save")
    CommonResult<Boolean> saveUser(@RequestBody @Validated UserDto userDto);

}
