package com.dcits.mongo;

import com.dcits.entity.City;
import com.dcits.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xuzzb
 * @Create 2022/4/12
 */
@Component
public interface MongoService {
    public List<City> getCityInfo();//查询Mongo信息
    public void insertEmployee(Employee employee);//新增一条数据
    public void insertEmployeeMany(List<Employee> employeeList,Employee employee);//批量插入数据
}
