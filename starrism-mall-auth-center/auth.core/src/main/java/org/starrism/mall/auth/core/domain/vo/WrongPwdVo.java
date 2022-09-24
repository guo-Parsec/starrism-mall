package org.starrism.mall.auth.core.domain.vo;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.starrism.mall.common.util.TextFormat;

import java.util.Map;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/9/24
 **/
@Setter
@Getter
public class WrongPwdVo {
    public static final String OVER_MAX_WRONG_COUNT_MSG = "您今日输错误密码已达到{count}次，您的账户已被锁定";

    public static final String NOT_OVER_MAX_WRONG_COUNT_MSG = "用户名或密码错误,您还剩余{count}次输错密码的机会，" +
            "如果您在{willLockTime}之前再次输错{count}次密码后, 您的账户将会被锁定{lockTime}个小时";
    private Integer currentWrongCount;

    private Integer maxWrongCount;

    private String willLockTime;

    private String lockTime;

    public String infoMessage() {
        if (currentWrongCount >= maxWrongCount) {
            return TextFormat.format(OVER_MAX_WRONG_COUNT_MSG, maxWrongCount);
        }
        Map<String, String> valueMap = Maps.newHashMapWithExpectedSize(3);
        valueMap.put("count", String.valueOf(maxWrongCount - currentWrongCount));
        valueMap.put("willLockTime", willLockTime);
        valueMap.put("lockTime", lockTime);
        return TextFormat.format(NOT_OVER_MAX_WRONG_COUNT_MSG, valueMap);
    }
}
