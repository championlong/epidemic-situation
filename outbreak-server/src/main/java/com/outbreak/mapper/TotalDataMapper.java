package com.outbreak.mapper;

import com.outbreak.entity.TotalData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TotalDataMapper {
    @Insert("insert into total_data(id,createTime,modifyTime,currentConfirmedCount,confirmedCount,suspectedCount,curedCount,deadCount,seriousCount,suspectedIncr,currentConfirmedIncr,confirmedIncr,curedIncr,deadIncr,seriousIncr,yesterdayConfirmedCountIncr,yesterdaySuspectedCountIncr)" +
            "values(#{id},#{createTime},#{modifyTime},#{currentConfirmedCount},#{confirmedCount},#{suspectedCount},#{curedCount},#{deadCount},#{seriousCount},#{suspectedIncr},#{currentConfirmedIncr},#{confirmedIncr},#{curedIncr},#{deadIncr},#{seriousIncr},#{yesterdayConfirmedCountIncr},#{yesterdaySuspectedCountIncr})")
    void addTotalData(TotalData totalData);

    @Select("select * from total_data")
    TotalData findTotalData ();

    @Update("update total_data set createTime=#{createTime},modifyTime=#{modifyTime},currentConfirmedCount=#{currentConfirmedCount},confirmedCount=#{confirmedCount},suspectedCount=#{suspectedCount},curedCount=#{curedCount},deadCount=#{deadCount},seriousCount=#{seriousCount},suspectedIncr=#{suspectedIncr},currentConfirmedIncr=#{currentConfirmedIncr},confirmedIncr=#{confirmedIncr},curedIncr=#{curedIncr},deadIncr=#{deadIncr},seriousIncr=#{seriousIncr},yesterdayConfirmedCountIncr=#{yesterdayConfirmedCountIncr},yesterdaySuspectedCountIncr=#{yesterdaySuspectedCountIncr}")
    void updateTotalData(TotalData totalData);
}