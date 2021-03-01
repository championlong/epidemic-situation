package com.outbreak.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.outbreak.entity.Location;
import com.outbreak.entity.TimeLine;
import com.outbreak.entity.TotalData;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {
    public static List<Location> parseLocationInformation(String locationInformation){
        List<Location> list = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(locationInformation);
        for(Object jsonObj : jsonArray){
            Location areaStat = JSON.toJavaObject((JSON) jsonObj, Location.class);
            list.add(areaStat);
        }
        return list;
    }

    public static TotalData parseTotalData(String totalData){
        JSONObject jsonObj = JSON.parseObject(totalData);
        TotalData total = JSON.toJavaObject(jsonObj, TotalData.class);
        total.setCreateTime(total.getCreateTime()/1000);
        total.setModifyTime(total.getModifyTime()/1000);
        return total;
    }

    public static List<TimeLine> parseTimeLineInformation(String timelineServiceInformation){
        List<TimeLine> list = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(timelineServiceInformation);
        for(Object jsonObj : jsonArray){
            TimeLine timeLine = JSON.toJavaObject((JSON) jsonObj,TimeLine.class);
            timeLine.setCreateTime(timeLine.getCreateTime()/1000);
            timeLine.setModifyTime(timeLine.getModifyTime()/1000);
            timeLine.setPubDate(timeLine.getPubDate()/1000);
            list.add(timeLine);
        }
        return list;
    }
}
