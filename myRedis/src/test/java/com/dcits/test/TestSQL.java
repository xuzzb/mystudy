package com.dcits.test;

import com.dcits.entity.City;
import com.dcits.mapper.CityMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
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
    @Resource
    private Redisson redisson;
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
    @Test
    public void booleanFilter(){
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("nameList");
        bloomFilter.tryInit(10000000L,0.02);
        bloomFilter.add("zhuge");
        bloomFilter.add("zhangsan");
        System.out.println(bloomFilter.contains("zhuge"));
        System.out.println(bloomFilter.contains("zhangsan"));
        System.out.println(bloomFilter.contains("dddd"));
    }
}
