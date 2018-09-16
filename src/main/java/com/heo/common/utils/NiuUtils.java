package com.heo.common.utils;

import com.heo.entity.mapper.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auth justinniu
 * @Date 2018/9/16
 * @Desc
 */
public class NiuUtils<T> {
    public static Object modityProperty(Object t, Class clazz) {
        Method[] methods = clazz.getMethods();
        clazz.getFields();
        List<Method> getMethods = new ArrayList<>();
        List<Method> setMethods = Arrays.stream(methods).filter(method -> method.getName().contains("set")).collect(Collectors.toList());
        try {
            for (int i = 0; i < setMethods.size(); i++) {
                String temp = setMethods.get(i).getName();
                String get = "g" + temp.substring(1);
//                System.out.println(get);
                getMethods.add(clazz.getDeclaredMethod(get));
            }

                Byte aByte = new Byte("0");
                Long aLong = new Long("0");

            for (int i = 0; i < setMethods.size(); i++) {
                String propertyName = getMethods.get(i).getReturnType().getSimpleName();
                System.out.println(propertyName);
                Object temp = getMethods.get(i).invoke(t);
                if (null == temp) {
                    switch (propertyName) {
                        case "Integer": {
                            setMethods.get(i).invoke(t, 0);
                            break;
                        }
                        case "String": {
                            setMethods.get(i).invoke(t, "");
                            break;
                        }
                        case "Long": {
                            setMethods.get(i).invoke(t, aLong);
                            break;
                        }
                        case "Byte": {
                            setMethods.get(i).invoke(t, aByte);
                            break;
                        }
                        case "Double": {
                            setMethods.get(i).invoke(t, 0.0);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        return t;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(1l);
        modityProperty(user, User.class);
        System.out.println(user.getUserName());
    }
}
