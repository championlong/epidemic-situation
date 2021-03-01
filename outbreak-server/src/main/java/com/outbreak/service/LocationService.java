package com.outbreak.service;

import com.outbreak.dto.ChinaDataResult;

import java.util.List;

public interface LocationService {

    void saveLocationInformation(String location);

    List<ChinaDataResult> selectAllList();
}
