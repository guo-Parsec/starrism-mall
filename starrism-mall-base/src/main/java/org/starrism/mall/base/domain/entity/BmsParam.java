package org.starrism.mall.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.data.domain.entity.BaseDataEntity;

/**
 * 系统参数表
 *
 * @author hedwing
 * @TableName bms_param
 */
@TableName(value = "bms_param")
@Setter
@Getter
public class BmsParam extends BaseDataEntity {
    private static final long serialVersionUID = -7109953093730050084L;

    /**
     * 参数标识
     */
    private String paramCode;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数名称
     */
    private String paramName;

}