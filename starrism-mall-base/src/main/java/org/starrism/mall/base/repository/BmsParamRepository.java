package org.starrism.mall.base.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.starrism.mall.base.domain.entity.BmsParam;
import org.starrism.mall.data.mapper.CoreMapper;

/**
 * <p>针对表【bms_param(系统参数表)】的数据库查询操作Repository</p>
 *
 * @author hedwing
 */
@Repository(value = "bmsParamRepository")
public interface BmsParamRepository extends CoreMapper<BmsParam> {
    /**
     * 根据参数码获取参数信息
     *
     * @param paramCode 参数码
     * @return 参数信息
     */
    BmsParam findByParamCode(@Param("paramCode") String paramCode);
}




