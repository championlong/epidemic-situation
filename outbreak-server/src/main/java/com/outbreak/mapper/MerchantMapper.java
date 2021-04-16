package com.outbreak.mapper;

import com.outbreak.entity.Merchant;
import com.outbreak.entity.TimeLine;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MerchantMapper {

    @Select("select * , ROUND(st_distance_sphere(POINT(lng, lat), POINT(#{lng}, #{lat}))) AS distance FROM merchant " +
            "HAVING distance > 0 AND distance < 5000 \n" +
            "ORDER BY distance")
    List<Merchant> selectMerchantList (@Param("lng") double lng, @Param("lat") double lat);

    @Select("select * from merchant where id=#{id}")
    Merchant selectMerchantById (@Param("id")int id);
}
