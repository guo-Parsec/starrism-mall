package org.starrism.mall.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.starrism.mall.data.domain.entity.BaseEntity;

import java.util.List;

/**
 * <p>核心Mapper</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
public interface CoreMapper<T extends BaseEntity> extends BaseMapper<T> {
    /**
     * <p>批量插入</p>
     *
     * @param list 实体list
     * @return int
     * @author hedwing
     * @since 2022/8/25
     */
    int insertAll(@Param("list") List<T> list);
}
