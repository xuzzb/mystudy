package com.dcits.mybatis.plus;

import com.dcits.mybatis.entity.City;
import com.dcits.mybatis.service.CityService;
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
    public String getCityInfoById(){
        City city =cityService.getCityInfoByCityId();
        return city.toString();
    }
    @RequestMapping("/getCityInfo")
    public String getCityInfo(){
        cityService.getCityInfo();
        return "vvvv";
    }
}
