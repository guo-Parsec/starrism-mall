package org.starrism.mall.admin.api.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Setter
@Getter
public class BmsDictVo implements Serializable {
    private static final long serialVersionUID = 8139600293382365675L;
    private String dictCode;

    private String dictValue;

    private Integer sort;
}
