package org.starrism.mall.common.log;

/**
 * <p>自定义日志生成工厂类</p>
 *
 * @author hedwing
 * @since 2022/8/31
 **/
public class StarrismLoggerFactory {
    public static StarrismLogger getLogger(Class<?> clz) {
        return new StarrismLogger(clz);
    }

    public static StarrismLogger getLogger(String name) {
        return new StarrismLogger(name);
    }
}
