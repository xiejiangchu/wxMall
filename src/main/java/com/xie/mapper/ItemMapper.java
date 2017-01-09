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
            @Result(property = "category_id1", column = "category_id1", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "category_id2", column = "category_id2", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_remain", column = "is_remain", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_online", column = "is_online", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_active", column = "is_active", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_rough", column = "is_rough", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_promote", column = "is_promote", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_delete", column = "is_delete", javaType = Integer.class, jdbcType = JdbcType.INTEGER),

            @Result(property = "weight", column = "weight", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "order_quantity", column = "order_quantity", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "max_quantity", column = "max_quantity", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "market_price", column = "market_price", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "shop_price", column = "shop_price", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "promote_price", column = "promote_price", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "remain", column = "remain", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "sale_num", column = "sale_num", javaType = Double.class, jdbcType = JdbcType.DECIMAL),

            @Result(property = "promote_end", column = "promote_end", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM goods WHERE id = #{id}")
    Item getById(@Param("id") int id);

    @Results({
            @Result(property = "category_id1", column = "category_id1", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "category_id2", column = "category_id2", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_remain", column = "is_remain", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_online", column = "is_online", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_active", column = "is_active", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_rough", column = "is_rough", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_promote", column = "is_promote", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_delete", column = "is_delete", javaType = Integer.class, jdbcType = JdbcType.INTEGER),

            @Result(property = "weight", column = "weight", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "order_quantity", column = "order_quantity", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "max_quantity", column = "max_quantity", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "market_price", column = "market_price", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "shop_price", column = "shop_price", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "promote_price", column = "promote_price", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "remain", column = "remain", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "sale_num", column = "sale_num", javaType = Double.class, jdbcType = JdbcType.DECIMAL),

            @Result(property = "promote_end", column = "promote_end", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM goods")
    List<Item> getAll();

    @Results({
            @Result(property = "category_id1", column = "category_id1", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "category_id2", column = "category_id2", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_remain", column = "is_remain", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_online", column = "is_online", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_active", column = "is_active", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_rough", column = "is_rough", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_promote", column = "is_promote", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "is_delete", column = "is_delete", javaType = Integer.class, jdbcType = JdbcType.INTEGER),

            @Result(property = "weight", column = "weight", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "order_quantity", column = "order_quantity", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "max_quantity", column = "max_quantity", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "market_price", column = "market_price", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "shop_price", column = "shop_price", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "promote_price", column = "promote_price", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "remain", column = "remain", javaType = Double.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "sale_num", column = "sale_num", javaType = Double.class, jdbcType = JdbcType.DECIMAL),

            @Result(property = "promote_end", column = "promote_end", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "created_at", column = "created_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "updated_at", column = "updated_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "deleted_at", column = "deleted_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM goods where category_id1 = #{level1} and category_id2 = #{level2}")
    List<Item> getByCategory(@Param("level1") int level1, @Param("level2") int level2);
}
