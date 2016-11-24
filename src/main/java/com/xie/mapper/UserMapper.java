package com.xie.mapper;

import com.xie.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") String id);

    @Select("SELECT * FROM users ")
    List<User> getAllUsers();
}
