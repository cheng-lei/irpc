package com.cxycds.irpc.protocol;

import lombok.*;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by chenglei on 2020/12/27.
 */
@Data
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static ServiceDescriptor build(Class clazz, Method method){
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());
        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i = 0; i < parameterClasses.length; i++) {
            parameterTypes[i] = parameterClasses[i].getName();
        }
        serviceDescriptor.setParameterTypes(parameterTypes);
        return serviceDescriptor;
    }

}
