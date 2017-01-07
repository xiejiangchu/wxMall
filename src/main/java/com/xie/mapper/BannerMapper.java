package com.xie.mapper;

import com.xie.bean.Banner;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Mapper
public interface BannerMapper {

    @Results({
            @Result(property = "created_at", column = "created_at",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM banner b order by b.order desc")
    public List<Banner> getAllBanners();

    @Results({
            @Result(property = "created_at", column = "created_at",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT b.* FROM banner b WHERE is_show = 1 order by b.order desc")
    public List<Banner> getAllBannersCanShow();

    @Results({
            @Result(property = "created_at", column = "created_at",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT b.* FROM banner b WHERE id = #{id}")
    public Banner getBannerById(@Param("id") int id);


//    @Insert("INSERT into village(name,district) VALUES(#{villageName}, #{district})")
//    void insertVillage(Village village);
//
//    @Update("UPDATE village SET name=#{villageName}, district =#{district} WHERE id =#{vid}")
//    void updateVillage(Village village);
//
//    @Delete("DELETE FROM village WHERE id =#{id}")
//    void deleteVillage(int id);
}
