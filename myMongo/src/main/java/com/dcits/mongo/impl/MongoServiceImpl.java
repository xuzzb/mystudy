package com.dcits.mongo.impl;

import com.dcits.entity.City;
import com.dcits.entity.Employee;
import com.dcits.mongo.MongoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xuzzb
 * @Create 2022/4/12
 */
@Log
@Component
public class MongoServiceImpl implements MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<City> getCityInfo() {
        //mongoTemplate.findAll();
        return null;
    }

    @Override
    public void insertEmployee(Employee employee) {
        log.info("单笔插入数据");
      mongoTemplate.insert(employee);
    }

    @Override
    public void insertEmployeeMany(List<Employee> employeeList, Employee employee) {
        log.info("批量插入数据");
    }
}
