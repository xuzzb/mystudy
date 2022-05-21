package com.dcits.mybatis.entity;

import lombok.Data;

/**
 * @Author xuzzb
 * @Create 2022/5/14
 */
public class City {
    private Integer id;//城市ID
    private String name;//城市名称
    private String countryCode;//城市代码
    private String district;//
    private Integer population;//城市人口



    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getPopulation() {
        return population;
    }

}
