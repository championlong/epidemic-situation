package com.outbreak.controller;

import com.outbreak.common.api.CommonResult;
import com.outbreak.dto.ChinaDataResult;
import com.outbreak.dto.TimeLineResult;
import com.outbreak.dto.TotalDataResult;
import com.outbreak.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dynameic")
public class TimeLineController {
    @Autowired
    private TimeLineService timeLineService;

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public CommonResult<List<TimeLineResult>> listLocation(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        List<TimeLineResult> timeLineResults= timeLineService.selectTimeLinList(page,pageSize);
        return CommonResult.success(timeLineResults);
    }
}
