package com.outbreak.service.impl;


import com.github.pagehelper.PageHelper;
import com.outbreak.dto.TimeLineResult;

import com.outbreak.entity.TimeLine;

import com.outbreak.mapper.TimeLineMapper;
import com.outbreak.service.TimeLineService;
import com.outbreak.utils.ParseUtils;
import com.outbreak.utils.RefactorStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class TimeLineServiceImpl implements TimeLineService {
    private static final String REGEX = "\\[(.*?)\\]";

    @Autowired
    private TimeLineMapper timeLineMapper;
    @Override
    public void saveTimeLine(String data) {
        String html = RefactorStringUtil.refactorString(data, REGEX);
        List<TimeLine> timeLineList = ParseUtils.parseTimeLineInformation(html);
        for (TimeLine timeLine : timeLineList) {
            if(timeLineMapper.selectTimeLineById(timeLine.getId()) == 0)
            {
                timeLineMapper.addTimeLine(timeLine);
            }
        }
    }

    @Override
    public List<TimeLineResult> selectTimeLinList(Integer page,Integer pageSize) {
        List<TimeLineResult> timeList = new ArrayList<>();
        PageHelper.startPage(page, pageSize);
        List<TimeLine> timeLineList = timeLineMapper.selectTimeLinList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (TimeLine timeLine : timeLineList) {
            TimeLineResult timeLineResult = new TimeLineResult();
            timeLineResult.setTitle(timeLine.getTitle());
            timeLineResult.setArticle(timeLine.getSummary());
            timeLineResult.setSource(timeLine.getInfoSource());
            timeLineResult.setSourceUrl(timeLine.getSourceUrl());
            String dateString = formatter.format(timeLine.getPubDate() * 1000);
            timeLineResult.setData(dateString);
            timeList.add(timeLineResult);
        }
        return timeList;
    }
}
