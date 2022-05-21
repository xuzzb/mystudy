package com.dcits;

/**
 * @Author xuzzb
 * @Create 2022/5/15
 * 普通内部类
 */
public class TestLambda {
    private static final String name = "张三";
    public static void main(String[] args){
      LambdaInterface lambdaInterface = new LambdaInterface() {
          @Override
          public void sayHello(String str) {
              System.out.println("str"+str);
          }
      };
      lambdaInterface.sayHello("dddddddddddddddddddd");
    }
}
