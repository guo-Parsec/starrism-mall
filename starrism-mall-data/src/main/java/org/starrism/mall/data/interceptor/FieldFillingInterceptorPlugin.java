package org.starrism.mall.data.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starrism.mall.common.annotation.Fill;
import org.starrism.mall.common.annotation.Id;
import org.starrism.mall.common.enums.FillStrategy;
import org.starrism.mall.common.enums.FillType;
import org.starrism.mall.common.util.ReflectionUtil;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.data.component.SnowflakeWorker;
import org.starrism.mall.data.domain.entity.BaseEntity;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>MyBatis主键生成拦截器插件</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class FieldFillingInterceptorPlugin implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(FieldFillingInterceptorPlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement arg = (MappedStatement) args[0];
        if (SqlCommandType.INSERT.name().equals(arg.getSqlCommandType().name())) {
            ParameterMap parameterMap = arg.getParameterMap();
            Class<?> type = parameterMap.getType();
            Object actArg = args[1];
            if (!(actArg instanceof BaseEntity)) {
                return invocation.proceed();
            }
            ReflectionUtil.getAllFields(type).forEach(field -> handleCore(field, actArg));
        }
        return invocation.proceed();
    }

    /**
     * <p>核心处理</p>
     *
     * @param field  属性
     * @param actArg 实际参数
     * @author hedwing
     * @since 2022/8/13
     */
    private void handleCore(Field field, Object actArg) {
        Id idAnnotation = field.getAnnotation(Id.class);
        Fill fillAnnotation = field.getAnnotation(Fill.class);
        if (idAnnotation != null) {
            handleId(field, actArg, idAnnotation);
        }
        if (fillAnnotation != null) {
            handleFieldFilling(field, actArg, fillAnnotation);
        }
    }

    /**
     * <p>处理id</p>
     *
     * @param field 属性
     * @param arg   实际参数
     * @author hedwing
     * @since 2022/8/13
     */
    private void handleId(Field field, Object arg, Id idAnnotation) {
        String name = field.getName();
        FillType fillType = idAnnotation.idType();
        boolean autoInjectCondition = fillType.equals(FillType.AUTO) && Objects.isNull(ReflectionUtil.getFieldValue(field, arg));
        if (fillType.equals(FillType.NONE)) {
            if (logger.isWarnEnabled()) {
                logger.warn("fill-type of field[id] {} is None, it will not be filled", name);
            }
            return;
        }
        if (fillType.equals(FillType.ALWAYS) || autoInjectCondition) {
            long nextId = SnowflakeWorker.getBean().nextId();
            logger.debug("field[id] {} will be filling by {}", name, nextId);
            ReflectionUtil.setField(field, arg, nextId);
        }
    }

    private void handleFieldFilling(Field field, Object arg, Fill fillAnnotation) {
        String name = field.getName();
        FillType fillType = fillAnnotation.fillType();
        FillStrategy strategy = fillAnnotation.strategy();
        String value = fillAnnotation.value();
        if (fillType.equals(FillType.NONE)) {
            if (logger.isWarnEnabled()) {
                logger.warn("fill-type of field {} is None, it will not be filled", name);
            }
            return;
        }
        boolean injectCondition = fillType.equals(FillType.ALWAYS)
                || (FillType.AUTO.equals(fillType) && Objects.isNull(ReflectionUtil.getFieldValue(field, arg)));
        if (FillStrategy.VALUE.equals(strategy)) {
            if (StrUtil.isBlank(value)) {
                logger.error("when fill-strategy of field {} is `VALUE`, the value cannot be empty", name);
                throw new RuntimeException("when fill-strategy is `VALUE`, the value cannot be empty");
            }
            if (injectCondition) {
                logger.debug("field {} will be filling by {}", name, value);
                ReflectionUtil.setField(field, arg, value);
            }
            return;
        }
        if (FillStrategy.DATETIME.equals(strategy)) {
            if (injectCondition) {
                if (field.getType().equals(LocalDateTime.class)) {
                    LocalDateTime now = LocalDateTime.now();
                    logger.debug("field[LocalDateTime] {} will be filling by {}", name, now);
                    ReflectionUtil.setField(field, arg, now);
                    return;
                }
                if (field.getType().equals(Date.class)) {
                    Date now = new Date();
                    logger.debug("field[Date] {} will be filling by {}", name, now);
                    ReflectionUtil.setField(field, arg, now);
                }
            }
        }
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.warn(properties.toString());
        Interceptor.super.setProperties(properties);
    }
}
