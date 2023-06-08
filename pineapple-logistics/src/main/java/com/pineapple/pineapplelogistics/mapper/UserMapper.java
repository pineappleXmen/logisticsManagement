package com.pineapple.pineapplelogistics.mapper;

import com.pineapple.pineapplelogistics.entity.User;
import com.pineapple.pineapplelogistics.vo.SignUpVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 19:11 2023/4/27
 * @Modifier by:
 */
@Mapper
public interface UserMapper {
    @Select("select * from t_user where email= #{username} or phone= #{username}")
    User findUserById(String username);

    @Select("select * from t_user where nickname= #{username}")
    User findUserByName(String username);

    @Insert("insert into t_user (phone, email, nickname, password, salt, avatar, registry_time, last_login_time, login_count, role) values " +
            "(#{phone},#{email},#{nickname},#{password},#{salt},#{avatar},#{registry_time},#{last_login_time},#{login_count},#{role})")
    int createNewUser(User user);

    @Update("update t_user set password = #{password} where email = #{email}")
    int updatePassword(String password,String email);

    @Update("update t_user set last_login_time = #{dateTime} where nickname = #{name}")
    int updateLastLoginTime(LocalDateTime dateTime,String name);

    @Update("update t_user set login_count = login_count + 1 where nickname = #{name}")
    int updateLoginCount(String name);
}
