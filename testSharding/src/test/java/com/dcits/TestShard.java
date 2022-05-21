package com.dcits;


import com.dcits.entity.Course;
import com.dcits.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xuzzb
 * @Create 2022/5/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestShard {
    @Autowired
    private CourseMapper courseMapper;
    @Test
    public void testCourse(){
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
    }
}
