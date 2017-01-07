package com.xie.mapper;

import com.xie.bean.Item;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Created by xie on 16/11/24.
 */
@Mapper
public interface ItemMapper {

    @Results({
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM goods WHERE id = #{id}")
    Item getById(@Param("id") int id);

    @Results({
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM goods")
    List<Item> getAll();

    @Results({
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM goods where category_id1 = #{level1} and category_id2 = #{level2}")
    List<Item> getByCategory(@Param("level1") int level1, @Param("level2") int level2);
}
