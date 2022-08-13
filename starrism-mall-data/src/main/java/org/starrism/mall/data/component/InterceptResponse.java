package org.starrism.mall.data.component;

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
import org.starrism.mall.common.rest.CommonResult;
import org.starrism.mall.common.util.ReflectionUtil;
import org.starrism.mall.data.domain.vo.DictVo;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>字典数据处理转换</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Order(1)
@ControllerAdvice
@SuppressWarnings("all")
public class InterceptResponse implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof CommonResult) {
            CommonResult commonResult = (CommonResult) body;
            if (commonResult != null) {
                Object data = commonResult.getData();
                Map<String, String> dictMap = new HashMap<>(12);
                if (data instanceof Collection) {
                    handleDataList((Collection) data, dictMap);
                } else {
                    handleData((Object) data, dictMap);
                }
            }
        }
        return body;
    }

    private void handleDataList(Collection<Object> dataCollection, Map<String, String> dictMap) {
        for (Object data : dataCollection) {
            handleData(data, dictMap);
        }
    }

    private void handleData(Object data, Map<String, String> dictMap) {
        List<Field> allFields = ReflectionUtil.getAllFields(data.getClass());
        for (Field field : allFields) {
            DictData dictData = field.getAnnotation(DictData.class);
            if (dictData != null) {
                String categoryCode = dictData.categoryCode();
                if (categoryCode != null) {
                    if (dictMap == null || dictMap.keySet().isEmpty()) {
                        DictComponent bean = SpringBean.getBean(DictComponent.class);
                        dictMap = bean.findDictByCategoryCode(categoryCode)
                                .stream().collect(Collectors.toMap(DictVo::getDictCode, DictVo::getDictValue));
                    }
                    Dict fieldValue = (Dict) ReflectionUtil.getFieldValue(field, data);
                    String dictCode = String.valueOf(fieldValue.getDictCode());
                    String value = dictMap.get(dictCode);
                    fieldValue.setDictValue(value);
                }
            }
        }
    }


}
