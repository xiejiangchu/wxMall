package com.xie.mapper;

import com.xie.bean.Category;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Mapper
public interface CategoryMapper {

    @Results({
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM category b order by b.order desc")
    public List<Category> getAllCategory();

    @Results({
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT b.* FROM category b WHERE is_delete = 0 order by b.order desc")
    public List<Category> getAllCategoryCanShow();

    @Results({
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT b.* FROM category b WHERE id = #{id}")
    public Category getById(@Param("id") int id);


    @Results({
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT b.* FROM category b WHERE is_delete = 0 and level = 1 order by b.order desc")
    public List<Category> getCategoryLevel1();

    @Results({
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT b.* FROM category b WHERE pid = #{pid} and is_delete = 0 and level = 2 order by b.order desc")
    public List<Category> getCategoryLevel2(@Param("pid") int pid);

    @Select("SELECT count(*) FROM category WHERE pid = #{cid1}")
    public int countCid2ByCid1(int cid1);

    @Delete("Delete FROM category WHERE id = #{id}")
    public int delete(int id);

}
