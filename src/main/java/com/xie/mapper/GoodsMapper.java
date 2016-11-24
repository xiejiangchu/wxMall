package com.xie.mapper;

import com.xie.bean.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */
@Mapper
public interface GoodsMapper {

    @Select("SELECT * FROM goods WHERE id = #{id}")
    Goods findById(@Param("id") String id);

    @Select("SELECT * FROM goods")
    List<Goods> getAll();
}
