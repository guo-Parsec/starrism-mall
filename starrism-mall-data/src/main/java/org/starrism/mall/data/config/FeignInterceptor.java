package org.starrism.mall.data.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.springframework.context.annotation.Configuration;

/**
 * <p>改造feign的请求拦截器，用于植入Seata用xid。同理其他的调用方式改造对应的拦截器即可</p>
 *
 * @author hedwing
 * @since 2022/9/1
 **/
@Configuration
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String xid = RootContext.getXID();
        requestTemplate.header(RootContext.KEY_XID, xid);
    }
}
