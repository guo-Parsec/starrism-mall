package org.starrism.mall.common.support;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>jwt对象</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
@Setter
@Getter
@ToString
public class JwtMeta {
    private static final String JWT_META_ID_KEY = "JWT_META_ID";
    private static final String JWT_META_SUBJECT_KEY = "JWT_META_SUBJECT";
    /**
     * id
     */
    private Long id;

    /**
     * 主题
     */
    private String subject;

    /**
     * 发布时间
     */
    private Date issuedAt;

    /**
     * 签发人
     */
    private String issuer;

    /**
     * 额外声明
     */
    private Map<String, Object> claim;

    /**
     * 过期时间
     */
    private Date expiration;

    public String toJsonString() {
        Map<String, Object> clone = new HashMap<>(claim);
        clone.put(JWT_META_ID_KEY, id);
        clone.put(JWT_META_SUBJECT_KEY, subject);
        return JSON.toJSONString(clone);
    }
}
