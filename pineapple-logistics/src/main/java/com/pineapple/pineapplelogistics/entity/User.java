package com.pineapple.pineapplelogistics.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:53 2023/4/24
 * @Modifier by:
 */



/**
 *
 * @TableName t_user
 */

@Data
public class User implements Serializable {
    /**
     *
     */

    private Long id;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String nickname;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String salt;

    /**
     *
     */
    private String avatar;

    /**
     *
     */
    private LocalDateTime registry_time;

    /**
     *
     */
    private LocalDateTime last_login_time;

    /**
     *
     */
    private Long login_count;

    /**
     *
     */
    private Integer role;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
                && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
                && (this.getRegistry_time() == null ? other.getRegistry_time() == null : this.getRegistry_time().equals(other.getRegistry_time()))
                && (this.getLast_login_time() == null ? other.getLast_login_time() == null : this.getLast_login_time().equals(other.getLast_login_time()))
                && (this.getLogin_count() == null ? other.getLogin_count() == null : this.getLogin_count().equals(other.getLogin_count()))
                && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getRegistry_time() == null) ? 0 : getRegistry_time().hashCode());
        result = prime * result + ((getLast_login_time() == null) ? 0 : getLast_login_time().hashCode());
        result = prime * result + ((getLogin_count() == null) ? 0 : getLogin_count().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", nickname=").append(nickname);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", avatar=").append(avatar);
        sb.append(", registry_time=").append(registry_time);
        sb.append(", last_login_time=").append(last_login_time);
        sb.append(", login_count=").append(login_count);
        sb.append(", role=").append(role);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

