package com.dcits.controller;

import com.dcits.entity.City;
import com.dcits.mapper.CityMapper;
import com.dcits.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xuzzb
 * @Create 2022/3/17
 */
@RestController
public class CityInfoController {
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CityService cityService;
    @RequestMapping(value = "/insertCityInfo")
    public String insertCityInfo(){
        City city = new City();
        int id = (int) (Math.random()*5000);
        city.setId(id);
        city.setName("hanzhongshi");
        city.setDistrict("dddddd");
        city.setPopulation(10000000);
        city.setCountryCode("NNA");
        City cityInfo = cityService.insertCityInfo(city);
        return cityInfo.toString();
    }
    @RequestMapping(value = "/updateCityInfo")
    public String updateCityInfo(){
        City city = new City();
        int id = (int) (Math.random()*5000);
        city.setId(id);
        city.setName("hanzhongshi");
        city.setDistrict("dddddd");
        city.setPopulation(10000000);
        city.setCountryCode("NNA");
        City cityinfo = cityService.updateCityInfo(city);
        return cityinfo.toString();
    }
    @RequestMapping("/getCityInfo")
    public City getCityInfo(String cityId){
        City city = null;
        Long startTime = System.currentTimeMillis() ;
        for (int i=100;i<3000;i++) {
            city = cityService.getCityIndo(i);
        }
        Long endTime = System.currentTimeMillis();
        Long allTime = endTime - startTime;
        System.out.println("总共耗时"+allTime);
        return city;
    }
}
