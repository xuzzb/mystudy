package com.dcits.contrller;

import com.dcits.entity.Employee;
import com.dcits.mongo.MongoService;
import com.dcits.mongo.impl.MongoServiceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author xuzzb
 * @Create 2022/4/12
 */
@Log
@RestController
@ConfigurationProperties(prefix = "secure.ignored")
public class MongoController {
    @Autowired
    private MongoServiceImpl mongoServiceImpl;
    @RequestMapping("/insertMongoInfo")
    public String getInfo(){
        log.info("这里是调用mongoDB");
        Employee employee = new Employee(1, "小明", 30,10000.00, new Date());
        mongoServiceImpl.insertEmployee(employee);
        return "mongo";
    }
}
