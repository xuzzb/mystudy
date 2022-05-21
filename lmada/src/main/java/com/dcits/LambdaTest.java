package com.dcits;

/**
 * @Author xuzzb
 * @Create 2022/5/15
 */
public class LambdaTest {
    public static void main(String[] args){
        //String str = "";
        LambdaInterface lambdaInterface = (String str)-> System.out.println(str);

        lambdaInterface.sayHello("This is lambda show ");
        int a = 10;
        LambdaInterfaceNo lambdaInterfaceNo = ()-> {
            //a = a ++;
            System.out.println(a+1);
        };
        lambdaInterfaceNo.sayWorld();
    }
}
