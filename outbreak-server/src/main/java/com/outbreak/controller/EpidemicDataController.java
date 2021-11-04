package com.outbreak.controller;

import com.outbreak.common.api.CommonResult;
import com.outbreak.dto.ChinaDataResult;
import com.outbreak.dto.TotalDataResult;
import com.outbreak.service.LocationService;
import com.outbreak.service.TotalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class EpidemicDataController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private TotalDataService totalDataService;

    @RequestMapping("/getChinaList")
    public CommonResult<List<ChinaDataResult>> listLocation(){
        List<ChinaDataResult> chinaDataResultList= locationService.selectAllList();
        return CommonResult.success(chinaDataResultList);
    }

    @RequestMapping("/getTotalData")
    public CommonResult<TotalDataResult> totalData(){
        TotalDataResult totalData = totalDataService.selectTotalData();
        return CommonResult.success(totalData);
    }
}
