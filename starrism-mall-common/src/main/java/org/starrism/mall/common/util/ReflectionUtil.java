package org.starrism.mall.common.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public class ReflectionUtil {
    private static final String SETTER_METHOD_PREFIX = "set";
    private static final String GETTER_METHOD_PREFIX = "get";

    /**
     * <p>获取全部属性 包括父类</p>
     *
     * @param clazz 类对象
     * @return java.util.List<java.lang.reflect.Field>
     * @author hedwing
     * @since 2022/8/13
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    public static <T> List<Field> getAllFields(T entity) {
        return getAllFields(entity.getClass());
    }

    public static List<Field> getAllFieldsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<Field> allFields = getAllFields(clazz);
        return allFields.stream().filter(field -> field.getAnnotation(annotationClass) != null)
                .collect(Collectors.toList());
    }

    public static <T> List<Field> getAllFieldsByAnnotation(T entity, Class<? extends Annotation> annotationClass) {
        return getAllFieldsByAnnotation(entity.getClass(), annotationClass);
    }

    public static <T> List<Field> getAllFieldsByAnnotation(T entity, Annotation annotation) {
        return getAllFieldsByAnnotation(entity.getClass(), annotation.getClass());
    }

    public static void setField(Field field, @NonNull Object target, @NonNull Object value) {
        Class<?> clz = target.getClass();
        String name = field.getName();
        if (StrUtil.isBlank(name)) {
            throw new RuntimeException("field name cannot be empty");
        }
        String setFieldMethodName = SETTER_METHOD_PREFIX + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, name);
        try {
            Method setFieldMethod = clz.getMethod(setFieldMethodName, field.getType());
            setFieldMethod.invoke(target, ConvertUtils.convert(value, field.getType()));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFieldByAccessible(Field field, @NonNull Object target, @NonNull Object value) {
        field.setAccessible(true);
        ReflectionUtils.setField(field, target, value);
        field.setAccessible(false);
    }

    public static Object getFieldValue(Field field, @NonNull Object target) {
        try {
            field.setAccessible(true);
            if (Objects.isNull(field.get(target))) {
                return null;
            }
            return ConvertUtils.convert(field.get(target), field.getType());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            field.setAccessible(false);
        }
    }
}

class Stu {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
