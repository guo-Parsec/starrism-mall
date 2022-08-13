package org.starrism.mall.admin.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.starrism.mall.data.entity.BaseDataEntity;

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
public class BmsUser extends BaseDataEntity {
    private static final long serialVersionUID = 3001411682177194704L;

    public String username;

    public String password;

    public String nickname;

    public String email;

    public String phoneNumber;

    public String avatarUrl;

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
