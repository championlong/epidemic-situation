package com.outbreak.service;

import com.outbreak.dto.TimeLineResult;


import java.util.List;

public interface TimeLineService {
    void saveTimeLine (String data);

    List<TimeLineResult> selectTimeLinList (Integer page,Integer pageSize);
}
