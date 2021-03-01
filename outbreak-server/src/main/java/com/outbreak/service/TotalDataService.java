package com.outbreak.service;

import com.outbreak.dto.TotalDataResult;

public interface TotalDataService {

    void saveTotalData(String data);

    TotalDataResult selectTotalData();
}
