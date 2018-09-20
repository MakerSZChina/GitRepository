package com.example.demo.dubbo.Service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.demo.dubbo.Entity.City;
import com.example.demo.dubbo.Service.CityService;
import org.springframework.stereotype.Component;

@Service(interfaceClass=CityService.class,group = "CityService",version = "1.0",loadbalance = "consistenthash")
@Component("cityServiceImpl")
public class CityServiceImpl implements CityService{

    @Override
    public City queryCityList() {
        City city = new City();
        city.setCityName("深圳");
        city.setCityCode("0755");
        city.setCityAddr("广东省珠江口鹏城");
        return city;
    }
}
