package org.starrism.mall.admin.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.starrism.mall.data.domain.entity.BaseDataEntity;

import java.util.Objects;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Setter
@Getter
@ToString
@TableName("bms_user")
public class BmsUser extends BaseDataEntity {
    private static final long serialVersionUID = 3001411682177194704L;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String phoneNumber;

    private String avatarUrl;

    private Integer sex;

    public BmsUser() {
    }

    public BmsUser(Long id) {
        super(id);
    }

    public BmsUser(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BmsUser bmsUser = (BmsUser) o;
        return Objects.equals(username, bmsUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username);
    }

    @Override
    public BmsUser clone() throws CloneNotSupportedException {
        return (BmsUser) super.clone();
    }
}
