package com.outbreak.mapper;

import com.outbreak.entity.TimeLine;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TimeLineMapper {

    @Insert("insert into time_line(id,pubDate,pubDateStr,title,summary,infoSource,sourceUrl,provinceName,createTime,modifyTime)values(#{id},#{pubDate},#{pubDateStr},#{title},#{summary},#{infoSource},#{sourceUrl},#{provinceName},#{createTime},#{modifyTime})")
    void addTimeLine (TimeLine timeLine);

    @Select("select count(*) from time_line where id = #{id}")
    int selectTimeLineById (int id);

    @Select("select * from time_line order by pubDate desc")
    List<TimeLine> selectTimeLinList ();
}
