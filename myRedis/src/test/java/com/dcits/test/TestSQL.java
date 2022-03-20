package com.dcits.test;

import com.dcits.entity.City;
import com.dcits.mapper.CityMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author xuzzb
 * @Create 2022/3/17
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSQL {
    @Resource
    private CityMapper cityMapper;
    @Test
    public void insertCityInfo(){
        City city = new City();
        city.setId(5000);
        city.setName("hanzhongshi");
        city.setDistrict("dddddd");
        city.setPopulation(10000000);
        city.setCountryCode("NNA");
        cityMapper.insertCityInfo(city);
    }

    @Test
    public void getCityInfo(){
        int id = 1000;
        City city = cityMapper.getCityInfo(id);
        System.out.println(city.toString());
    }

    @Test
    public void updateCityInfo(){
        City city = new City();
        city.setId(5000);
        city.setName("haeshi");
        city.setDistrict("dddddd");
        city.setPopulation(10000000);
        city.setCountryCode("NNA");
        cityMapper.updateCityInfo(city);
    }
}
