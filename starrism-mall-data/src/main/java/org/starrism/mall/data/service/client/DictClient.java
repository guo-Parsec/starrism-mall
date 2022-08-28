package org.starrism.mall.data.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.starrism.mall.common.util.SpringUtil;
import org.starrism.mall.common.util.StrUtil;
import org.starrism.mall.common.valid.Valid;
import org.starrism.mall.data.domain.vo.DictVo;
import org.starrism.mall.data.service.DictService;

/**
 * <p>数据字典转化客户端</p>
 *
 * @author hedwing
 * @since 2022/8/28
 **/
public class DictClient implements Valid {
    private static final Logger log = LoggerFactory.getLogger(DictClient.class);

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
        DictService bmsDictClient = context.getBean("dictService", DictService.class);
        DictVo dictVo = bmsDictClient.findDictByCodes(categoryCode, dictCode);
        if (dictVo == null) {
            return null;
        }
        return dictVo.getDictValue();
    }
}
