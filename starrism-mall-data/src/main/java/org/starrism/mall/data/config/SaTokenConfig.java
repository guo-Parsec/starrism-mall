package org.starrism.mall.data.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@EnableWebMvc
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    private final Logger log = LoggerFactory.getLogger(SaTokenConfig.class);

    /**
     * 注册Sa-Token的注解拦截器，打开注解式鉴权功能
     * @param registry 注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
    }

    // @Bean
    // public SaServletFilter getSaServletFilter() {
    //     return new SaServletFilter()
    //             .addInclude("/**")
    //             .addExclude("/favicon.ico", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**", "/error")
    //             .setAuth(obj -> {
    //                 // 校验 Id-Token 身份凭证     —— 以下两句代码可简化为：SaIdUtil.checkCurrentRequestToken();
    //                 String token = SaHolder.getRequest().getHeader(SaIdUtil.ID_TOKEN);
    //                 log.info("manage子服务当前的ID_TOKEN:{}", token);
    //                 SaIdUtil.checkToken(token);
    //                 //SaIdUtil.checkToken(StpUtil.getTokenValue());
    //
    //             })
    //             .setError(e -> {
    //                 e.printStackTrace();
    //                 return SaResult.error("manage子服务当前的异常" + e.getMessage());
    //             });
    // }
}
