package org.starrism.mall.base.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.starrism.mall.base.access.DictAccess;
import org.starrism.mall.common.support.CommonConverts;
import org.starrism.mall.common.util.SpringUtil;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.base.domain.vo.DictVo;

/**
 * <p>数据字典转化上下文</p>
 *
 * @author hedwing
 * @since 2022/8/28
 **/
public class DictContext {
    private static final Logger log = LoggerFactory.getLogger(DictContext.class);

    /**
     * <p>字典转换</p>
     *
     * @param categoryCode 分类码
     * @param dictCode     字典码
     * @return java.lang.String 字典值
     * @author hedwing
     * @since 2022/8/28
     */
    public static String convert(String categoryCode, Integer dictCode) {
        return convert(categoryCode, CommonConverts.toStr().convert(dictCode, StrUtil.EMPTY));
    }

    /**
     * <p>字典转换</p>
     *
     * @param categoryCode 分类码
     * @param dictCode     字典码
     * @return java.lang.String 字典值
     * @author hedwing
     * @since 2022/8/28
     */
    public static String convert(String categoryCode, String dictCode) {
        if (StrUtil.isBlank(categoryCode) || StrUtil.isBlank(dictCode)) {
            log.error("categoryCode与dictCode不能为空");
            return null;
        }
        ApplicationContext context = SpringUtil.getApplicationContext();
        DictAccess dictAccess = context.getBean("dictAccess", DictAccess.class);
        DictVo dictVo = dictAccess.findDictByCodes(categoryCode, dictCode);
        if (dictVo == null) {
            return null;
        }
        return dictVo.getDictValue();
    }
}
