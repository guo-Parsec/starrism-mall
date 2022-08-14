package org.starrism.mall.data.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.starrism.mall.common.annotation.DictData;
import org.starrism.mall.common.domain.Dict;
import org.starrism.mall.common.domain.Domainizable;
import org.starrism.mall.common.rest.CommonResult;
import org.starrism.mall.common.util.ReflectionUtil;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.data.domain.vo.DictVo;

import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>字典数据处理转换</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Order(1)
@ControllerAdvice
@SuppressWarnings({"rawtypes","unchecked"})
public class InterceptResponse implements ResponseBodyAdvice<Object> {
    private static final Logger log = LoggerFactory.getLogger(InterceptResponse.class);

    @SuppressWarnings({"all"})
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SuppressWarnings({"all"})
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof CommonResult) {
            CommonResult<?> commonResult = (CommonResult<?>) body;
            Object data = commonResult.getData();
            if (data != null) {
                if (data instanceof Collection) {
                    handleCollectionElement((Collection) data);
                } else {
                    handleSingleElement((Object) data);
                }
            }

        }
        return body;
    }

    /**
     * 处理集合数据
     *
     * @param processElements 处理元素集合
     */
    private void handleCollectionElement(Collection<Object> processElements) {
        for (Object processElement : processElements) {
            handleSingleElement(processElement);
        }
    }

    /**
     * 处理单个数据
     *
     * @param processElement 处理元素
     */
    private void handleSingleElement(Object processElement) {
        if (processElement instanceof Domainizable) {
            ReflectionUtil.getAnnotationPair(processElement, DictData.class)
                    .stream().filter(Objects::nonNull)
                    .forEach(pair -> dictConvert(pair, processElement));
        }
    }

    /**
     * <p>字段转换处理</p>
     *
     * @param pair           属性注解对
     * @param processElement 处理元素
     * @author hedwing
     * @since 2022/8/14
     */
    private void dictConvert(ReflectionUtil.FieldAnnotationPair<DictData> pair, Object processElement) {
        Field field = pair.getField();
        DictData dictData = pair.getAnnotation();
        if (field == null) {
            log.warn("filed of pair cannot be null");
            return;
        }
        if (dictData == null) {
            log.warn("dictData of pair cannot be null");
            return;
        }
        String categoryCode = dictData.categoryCode();
        Dict dict = (Dict) ReflectionUtil.getFieldValue(field, processElement);
        if (dict == null) {
            log.warn("convert object [{}] dict-filed [{}] `fieldValue` is null", processElement, field.getName());
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
