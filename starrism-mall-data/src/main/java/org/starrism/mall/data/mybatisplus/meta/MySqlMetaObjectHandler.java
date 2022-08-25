package org.starrism.mall.data.mybatisplus.meta;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>mysql数据元对象字段填充</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
@Component
public class MySqlMetaObjectHandler implements MetaObjectHandler {
    private static final Logger log = LoggerFactory.getLogger(MySqlMetaObjectHandler.class);

    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            log.debug("start insert fill...");
        }
        this.strictInsertFill(metaObject, "addTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            log.debug("start update fill...");
        }
        this.strictUpdateFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
    }
}
