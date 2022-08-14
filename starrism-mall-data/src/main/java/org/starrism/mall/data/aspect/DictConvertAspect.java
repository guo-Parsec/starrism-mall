package org.starrism.mall.data.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.starrism.mall.common.annotation.DictData;
import org.starrism.mall.common.domain.Dict;
import org.starrism.mall.common.util.ReflectionUtil;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.data.component.DictComponent;
import org.starrism.mall.data.component.SpringBean;
import org.starrism.mall.data.domain.vo.DictVo;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/14
 **/
@Component
@Aspect
public class DictConvertAspect {
    private static final Logger log = LoggerFactory.getLogger(DictConvertAspect.class);

    @Pointcut("@annotation(org.starrism.mall.common.annotation.DictConverter)")
    public void pointCut() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @AfterReturning(value = "pointCut()", returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj) throws Throwable {
        List<Field> fields = ReflectionUtil.getAllFieldsByAnnotation(returnObj, DictData.class);
        for (Field field : fields) {
            DictData dictData = field.getAnnotation(DictData.class);
            String categoryCode = dictData.categoryCode();
            Dict dict = (Dict) ReflectionUtil.getFieldValue(field, returnObj);
            if (dict == null) {
                log.warn("convert object [{}] dict-filed [{}] `fieldValue` is null", returnObj, field.getName());
                return;
            }
            String dictCode = String.valueOf(dict.getDictCode());
            DictComponent bean = SpringBean.getBean(DictComponent.class);
            if (StrUtil.isBlank(dictCode)) {
                log.warn("dict-filed [{}] dictCode cannot be empty", field.getName());
                return;
            }
            DictVo dictVo = Optional.ofNullable(bean.findDictByCodes(categoryCode, dictCode)).orElse(new DictVo());
            String dictValue = dictVo.getDictValue();
            if (StrUtil.isBlank(dictValue)) {
                log.warn("cannot find dict-value of dict-code[{}]", dictCode);
                return;
            }
            dict.setDictValue(dictValue);
        }
    }
}
