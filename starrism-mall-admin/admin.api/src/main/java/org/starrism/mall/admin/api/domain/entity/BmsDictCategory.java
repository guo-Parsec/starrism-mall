package org.starrism.mall.admin.api.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.data.entity.BaseDataEntity;

import java.util.List;

/**
 * <p>后台数据字典类别表</p>
 *
 * @author hedwing
 * @since  2022/8/13
 **/
@Getter
@Setter
public class BmsDictCategory extends BaseDataEntity {
    private static final long serialVersionUID = -3429255454672506646L;
    /**
    * 字典类别码
    */
    private String dictCategoryCode;

    /**
    * 字典类别名称
    */
    private String dictCategoryName;

    /**
    * 排序
    */
    private Integer sort;

    private List<BmsDictDetail> bmsDictDetails;
}