package com.cxycds.irpc.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenglei on 2020/12/27.
 */
public final class ReflectionUtils {
    public static <T> T newInstance(Class<T> tClass) {
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static Method[] getPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> resultMethods = new ArrayList<>(methods.length);
        for (Method m : methods) {
            if (Modifier.isPublic(m.getModifiers())) {
                resultMethods.add(m);
            }
        }
        return resultMethods.toArray(new Method[0]);
    }

    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
