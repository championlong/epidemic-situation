package com.outbreak.mapper;

import com.outbreak.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select count(*) from user where phone = #{phone}")
    int selectPhoneCount(String phone);

    @Select("select count(*) from user where username = #{username}")
    int selectNameCount(String username);

    @Insert("insert into user(username,password,email,created,salt) values(#{username},#{password},#{email},#{created},#{salt})")
    int insertSelective(User user);

    @Select("select * from user where username = #{username}")
    User selectByName(String username);
}
