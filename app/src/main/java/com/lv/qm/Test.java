package com.lv.qm;

import java.lang.reflect.Field;

/**
 * 作者：created by albert on 2018/9/28 14:26
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class Test {
    @TestAnnotation("Hello World!")
    private String testnnotation;

    public static void main(String[] arge) {

        try {
            Class cls = Class.forName("com.lv.qm.Test");
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                TestAnnotation annocation = field.getAnnotation(TestAnnotation.class);
                if (annocation != null) {
                    String value = annocation.value();
                    System.out.print("value:" + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
