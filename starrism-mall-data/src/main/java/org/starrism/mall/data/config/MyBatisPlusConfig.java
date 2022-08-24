package org.starrism.mall.data.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Configuration
@MapperScan({"com.baomidou.mybatisplus.samples.quickstart.mapper",
        "org.starrism.mall.**.mapper"})
public class MyBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 防止全表更新与删除：攻击SQL阻断解析器、加入解析链，防止恶意进行delete、update全表操作
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        // 自动分页
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        pageInterceptor.setMaxLimit(5000L);

        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }

}
