package com.dcits.controller;

import com.dcits.entity.Course;
import com.dcits.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xuzzb
 * @Create 2022/5/19
 */

public class UserController {
    @Autowired
    private CourseMapper courseMapper;
    public List<Course> setCourse(){
        List<Course> courses = new ArrayList<>();
        for(int i=0;i<10;i++){
            Course course = new Course();
            course.setCid(i);
            course.setCname("zhang"+i);
            course.setUserid(i*3);
            course.setCstatus("1");
            courses.add(course);
            courseMapper.insert(course);
        }
        return courses;
    }
}
