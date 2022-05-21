package com.dcits.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author xuzzb
 * @Create 2022/5/18
 */
public class CityInfo {
    private Integer id;//城市ID
    private String name;//城市名称
    private String countryCode;//城市代码
    private String district;//
    private Integer population;//城市人口
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    private String cityCode;
    private String cityName;
    private String testCode;

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

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
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

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getTestCode() {
        return testCode;
    }
}
