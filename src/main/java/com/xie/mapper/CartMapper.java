package com.xie.mapper;

import com.xie.bean.Cart;
import com.xie.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Mapper
public interface CartMapper {

    @Results({
            @Result(property = "user", column = "uid", one = @One(select = "com.xie.mapper.UserMapper.getById")),
            @Result(property = "item", column = "gid", one = @One(select = "com.xie.mapper.ItemMapper.getById")),
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("select * from cart where uid = #{uid}")
    List<Cart> getByUid(@Param("uid") int uid);
}
