package com.outbreak.service.impl;

import com.outbreak.dto.ChinaDataResult;
import com.outbreak.mapper.LocationMapper;
import com.outbreak.utils.ParseUtils;
import com.outbreak.utils.RefactorStringUtil;
import com.outbreak.entity.Location;
import com.outbreak.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private static final String REGEX = "\\[(.*)\\]";

    @Autowired
    LocationMapper locationMapper;

    @Override
    public void saveLocationInformation(String location) {
        String[] specialName = {"锡林郭勒盟", "兴安盟", "阿克苏地区", "客什地区", "神农架林区", "吉林市"};
        String[] provinceName = {"北京市", "上海市", "重庆市", "天津市"};
        String html = RefactorStringUtil.refactorString(location, REGEX);
        List<Location> locationList = ParseUtils.parseLocationInformation(html);
        for (Location loc : locationList) {
            Location oldLocation = locationMapper.findProvinceByName(loc.getProvinceName());
            if (oldLocation != null) {
                if (loc.equals(oldLocation)) {
                    System.out.println("无");
                } else {
                    loc.setModifyTime(System.currentTimeMillis() / 1000);
                    locationMapper.updateProvince(loc);
                }
            } else {
                loc.setModifyTime(System.currentTimeMillis() / 1000);
                locationMapper.addProvince(loc);
            }
            List<Location.CitiesBean> cityList = loc.getCities();
            for (Location.CitiesBean city : cityList) {
                Boolean isCity = true;
                Boolean isProvince = true;
                String cityNameity = city.getCityName();
                String provinceNameity = loc.getProvinceName();
                for (String special : specialName) {
                    if (special.equals(cityNameity)) {
                        isCity = false;
                        break;
                    } else {
                        continue;
                    }
                }
                for (String specialProvince : provinceName) {
                    if (specialProvince.equals(provinceNameity)) {
                        isProvince = false;
                        break;
                    } else {
                        continue;
                    }
                }
                if (isCity == isProvince) {
                    if (cityNameity.equals("大兴安岭")) {
                        city.setCityName(cityNameity + "地区");
                    } else {
                        city.setCityName(cityNameity + "市");
                    }
                }
                city.setProvinceName(loc.getProvinceName());
                Location.CitiesBean oldCity = locationMapper.findCityByName(city.getCityName());
                if (oldCity != null) {
                    if (oldCity.equals(city)) {
                        System.out.println("无新增城市");
                    } else {
                        city.setModifyTime(System.currentTimeMillis() / 1000);
                        locationMapper.updateCity(city);
                    }
                } else {
                    city.setModifyTime(System.currentTimeMillis() / 1000);
                    locationMapper.addCity(city);
                }
            }
        }
    }

    @Override
    public List<ChinaDataResult> selectAllList() {
        String cityName;
        List<ChinaDataResult> chinaDataList = new ArrayList<>();
        List<Location.CitiesBean> locationMapperCityAll = locationMapper.findCityAll();
        List<Location> locationMapperProvinceAll = locationMapper.findProvinceAll();
        for (Location.CitiesBean city : locationMapperCityAll) {
            ChinaDataResult chinaDataResult = new ChinaDataResult();
            cityName = city.getCityName();
            chinaDataResult.setName(city.getCityName());
            chinaDataResult.setValue(String.valueOf(city.getCurrentConfirmedCount()));
            chinaDataList.add(chinaDataResult);
        }

        for (Location province : locationMapperProvinceAll) {
            ChinaDataResult chinaDataResult = new ChinaDataResult();
            chinaDataResult.setName(province.getProvinceShortName());
            chinaDataResult.setValue(String.valueOf(province.getCurrentConfirmedCount()));
            chinaDataList.add(chinaDataResult);
        }
        return chinaDataList;
    }
}
