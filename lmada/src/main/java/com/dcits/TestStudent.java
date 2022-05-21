package com.dcits;

/**
 * @Author xuzzb
 * @Create 2022/5/15
 */
public class TestStudent {
    public static void main(String[] args){
       // String name = ;
//       LambdaStudent lambdaStudent = MethodEntity::test;
//       MethodEntity methodEntity = new MethodEntity();
//       lambdaStudent.getStudent(methodEntity);
       Student student = new Student();
       student.testL(MethodEntity::test);
    }
}
