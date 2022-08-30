package org.starrism.mall.admin.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.starrism.mall.base.domain.entity.BmsParam;
import org.starrism.mall.data.mapper.CoreMapper;

/**
 * <p>针对表【bms_param(系统参数表)】的数据库操作Mapper</p>
 *
 * @author hedwing
 */
@Repository(value = "bmsParamMapper")
@Mapper
public interface BmsParamMapper extends CoreMapper<BmsParam> {

}




