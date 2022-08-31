package org.starrism.mall.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.starrism.mall.data.domain.entity.BaseDataEntity;

/**
 * <p>后台字典详情表</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Getter
@Setter
@ToString
@TableName("bms_dict_detail")
public class BmsDictDetail extends BaseDataEntity {
    private static final long serialVersionUID = 7748288892147961697L;
    /**
     * 字典类别主键
     */
    private Long dictCategoryId;

    /**
     * 字典码
     */
    private String dictCode;

    /**
     * 字典值
     */
    private String dictValue;

    /**
     * 上级字典主键
     */
    private Long parentId;

    /**
     * 层级路径
     */
    private String hierarchicalPath;

    /**
     * 排序
     */
    @TableField(value = "`sort`")
    private Integer sort;

    @TableField(exist = false)
    private BmsDictCategory bmsDictCategory;
}