package org.starrism.mall.data.domain.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Setter
@Getter
public class DictVo extends BaseVo {
    private static final long serialVersionUID = 8139600293382365675L;
    private String dictCode;

    private String dictValue;

    private Integer sort;

    public DictVo() {
    }

    public DictVo(String dictCode, String dictValue) {
        this.dictCode = dictCode;
        this.dictValue = dictValue;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public static DictVo of(String dictCode, String dictValue) {
        return new DictVo(dictCode, dictValue);
    }

    public static DictVo of(String dictCode) {
        return new DictVo(dictCode, null);
    }
}
