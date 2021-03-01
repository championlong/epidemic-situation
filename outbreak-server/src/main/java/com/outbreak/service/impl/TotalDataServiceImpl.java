package com.outbreak.service.impl;

import com.outbreak.dto.TagDataResult;
import com.outbreak.dto.TotalDataResult;
import com.outbreak.entity.TotalData;
import com.outbreak.mapper.TotalDataMapper;
import com.outbreak.service.TotalDataService;
import com.outbreak.utils.ParseUtils;
import com.outbreak.utils.RefactorStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TotalDataServiceImpl implements TotalDataService {
    private static final String REGEX = "\\{(\"id\".*)\\}";

    @Autowired
    TotalDataMapper totalDataMapper;
    @Override
    public void saveTotalData(String data) {
        String html = RefactorStringUtil.refactorString(data, REGEX);
        String htmlData = html.replace("}catch(e){}","");
        TotalData totalData = ParseUtils.parseTotalData(htmlData);
        TotalData oldTotalData = totalDataMapper.findTotalData();
        if (oldTotalData != null){
            if (totalData.equals(oldTotalData)){
                return;
            }else {
                totalDataMapper.updateTotalData(totalData);
            }
        } else {
            totalDataMapper.addTotalData(totalData);
        }
    }

    @Override
    public TotalDataResult selectTotalData() {
        TotalData totalData = totalDataMapper.findTotalData();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(totalData.getModifyTime() * 1000);
        TotalDataResult totalDataResult = new TotalDataResult();
        totalDataResult.setDate(dateString);
        List<TagDataResult> tagList = new ArrayList<>();
        tagList.add(new TagDataResult("current","现有确诊","#FF0000",totalData.getCurrentConfirmedCount(),totalData.getCurrentConfirmedIncr()));
        tagList.add(new TagDataResult("suspected","境外输入","#FFCC00",totalData.getSuspectedCount(),totalData.getSuspectedIncr()));
        tagList.add(new TagDataResult("serious","现存无症状","#FF961E",totalData.getSeriousCount(),totalData.getSeriousIncr()));
        tagList.add(new TagDataResult("confirmed","累计确诊","#F55253",totalData.getConfirmedCount(),totalData.getConfirmedIncr()));
        tagList.add(new TagDataResult("cured","累计治愈","#178B50",totalData.getCuredCount(),totalData.getCuredIncr()));
        tagList.add(new TagDataResult("dead","累计死亡","#66666c",totalData.getDeadCount(),totalData.getDeadIncr()));
        totalDataResult.setTagList(tagList);
        System.out.println(dateString);
        return totalDataResult;
    }
}
