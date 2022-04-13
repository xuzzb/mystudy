package com.dcist.mapper;

import com.dcist.entity.City;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author xuzzb
 * @Create 2022/3/17
 */
@Mapper
public interface CityMapper {
    public void insertCityInfo(City city);
    public City getCityInfo(Integer id);
    public void updateCityInfo(City city);
}
