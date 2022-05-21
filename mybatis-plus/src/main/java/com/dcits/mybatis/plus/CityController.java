package com.dcits.mybatis.plus;

import com.dcits.mybatis.entity.City;
import com.dcits.mybatis.entity.CityInfo;
import com.dcits.mybatis.service.CityService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xuzzb
 * @Create 2022/5/14
 */
@RestController
@RequestMapping("/getCity")
public class CityController {
    @Autowired
    private CityService cityService;
    @RequestMapping("/getCityInfoById")
    public CityInfo getCityInfoById(){
        CityInfo cityInfo = new CityInfo();
        City city =cityService.getCityInfoByCityId();
        BeanUtils.copyProperties(city,cityInfo);
        System.out.println(cityInfo);
        return cityInfo;
    }
    @RequestMapping("/getCityInfo")
    public String getCityInfo(){
        cityService.getCityInfo();
        return "vvvv";
    }
}
