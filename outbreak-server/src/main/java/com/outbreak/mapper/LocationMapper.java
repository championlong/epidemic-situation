package com.outbreak.mapper;

import com.outbreak.dto.ChinaDataResult;
import com.outbreak.entity.Location;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface LocationMapper {

    /**
     * 添加省疫情
     * @param location
     * @return
     */
    @Insert("insert into province(provinceName,provinceShortName,currentConfirmedCount,confirmedCount,suspectedCount,curedCount,deadCount,`comment`,modifyTime) " +
            "values(#{provinceName},#{provinceShortName},#{currentConfirmedCount},#{confirmedCount},#{suspectedCount},#{curedCount},#{deadCount},#{comment},#{modifyTime})")
    void addProvince(Location location);

    /**
     * 添加市疫情
     * @param city
     * @return
     */
    @Insert("insert into city(cityName,currentConfirmedCount,confirmedCount,suspectedCount,curedCount,deadCount,provinceName,modifyTime) " +
            "values(#{cityName},#{currentConfirmedCount},#{confirmedCount},#{suspectedCount},#{curedCount},#{deadCount},#{provinceName},#{modifyTime})")
    void addCity(Location.CitiesBean city);

    @Select("select provinceShortName,currentConfirmedCount from province")
    List<Location> findProvinceAll ();

    @Select("select * from province where provinceName=#{provinceName}")
    Location findProvinceByName (String provinceName);

    @Select("select cityName,currentConfirmedCount from city")
    List<Location.CitiesBean> findCityAll ();

    @Select("select * from city where cityName=#{cityName}")
    Location.CitiesBean findCityByName (String cityName);

    @Select("select * from city where provinceName=#{provinceName}")
    List<Location.CitiesBean> findCityByProvince (String provinceName);

    @Update("update province set currentConfirmedCount=#{currentConfirmedCount},confirmedCount=#{confirmedCount},suspectedCount=#{suspectedCount},curedCount=#{curedCount}," +
            "deadCount=#{deadCount},`comment`=#{comment},modifyTime=#{modifyTime} where provinceShortName=#{provinceShortName}")
    void updateProvince(Location location);

    @Update("update city set currentConfirmedCount=#{currentConfirmedCount},confirmedCount=#{confirmedCount},suspectedCount=#{suspectedCount},curedCount=#{curedCount}," +
            "deadCount=#{deadCount} where cityName=#{cityName} and provinceName=#{provinceName}")
    void updateCity(Location.CitiesBean city);
}
