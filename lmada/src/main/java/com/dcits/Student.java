package com.dcits;

/**
 * @Author xuzzb
 * @Create 2022/5/15
 */
public class Student {
    private String name;

    public String getName() {
        System.out.println(name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void testL(LambdaStudent lambdaStudent){
        MethodEntity methodEntity = new MethodEntity();
        lambdaStudent.getStudent(methodEntity);
    }
}
