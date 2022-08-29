package org.starrism.mall.admin.core.mapper;

import org.apache.ibatis.annotations.Param;
import org.starrism.mall.admin.core.domain.entity.BmsParam;
import org.starrism.mall.data.mapper.CoreMapper;

/**
 * <p>针对表【bms_param(系统参数表)】的数据库操作Mapper</p>
 *
 * @author hedwing
 */
public interface BmsParamMapper extends CoreMapper<BmsParam> {
    /**
     * 根据参数码获取参数信息
     *
     * @param paramCode 参数码
     * @return 参数信息
     */
    BmsParam findByParamCode(@Param("paramCode") String paramCode);
}




