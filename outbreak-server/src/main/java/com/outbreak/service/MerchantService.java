package com.outbreak.service;

import com.outbreak.dto.MerchantResult;
import com.outbreak.entity.Merchant;

import java.util.List;
import java.util.Map;

public interface MerchantService {
    Map<Integer,MerchantResult> selectMerchantList();
}
