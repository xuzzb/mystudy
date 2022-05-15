package com.dcits.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dcits.mybatis.entity.City;
import com.dcits.mybatis.mapper.CityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xuzzb
 * @Create 2022/5/14
 */
@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;

    /**
     *
     * @return
     */
    public List<City> getCityInfo(){
        Page<City> page = new Page<City>(2,2);
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        Page<City> cities = cityMapper.selectPage(page,queryWrapper);
        List<City> cityList = cities.getRecords();
        for (City city: cityList) {
            System.out.println("This is cities info ===>"+city);
        }
        return null;
    }
    public City getCityInfoByCityId(){
        City city = cityMapper.selectById("12");
        System.out.println("This is the city info ===>" + city);
        return city;
    }
}
