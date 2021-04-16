package com.outbreak.service.impl;

import com.outbreak.dto.MerchantResult;
import com.outbreak.entity.Merchant;
import com.outbreak.mapper.MerchantMapper;
import com.outbreak.service.MerchantSerivce;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MerchantServiceImpl implements MerchantSerivce {

    private static Map<Integer,MerchantResult> map = new ConcurrentHashMap<>();
    private static double area;
    private static double people;

    public static MerchantResult getMerchantResult(int id){
        return map.get(id);
    }

    public static Map<Integer,MerchantResult> getMap(){
        return map;
    }

    public static MerchantResult setMap(int id,MerchantResult merchantResult){
        return map.put(id,merchantResult);
    }

    @Autowired
    MerchantMapper merchantMapper;

    @Override
    public Map<Integer,MerchantResult> selectMerchantList() {
        List<Merchant> merchantList = merchantMapper.selectMerchantList(126.608940,45.721195);
        for(Merchant merchant : merchantList){
            MerchantResult merchantResult = new MerchantResult();
            merchantResult.setLat(merchant.getLat());
            merchantResult.setLng(merchant.getLng());
            merchantResult.setPlace(merchant.getName());
            merchantResult.setPresent(merchant.getPresent());
            if (merchant.getMaximum() == 0){
                area = Math.PI * 0.75 * 0.75;
                people = merchant.getUsableArea()/area;
                merchantResult.setTotal((int)people);
            }
            merchantResult.setLat(merchant.getLat());
            map.put(merchant.getId(),merchantResult);
        }
        System.out.println(map.toString());

        return map;
    }
}
