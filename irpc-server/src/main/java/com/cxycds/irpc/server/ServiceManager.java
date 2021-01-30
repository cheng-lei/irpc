package com.cxycds.irpc.server;

import com.cxycds.irpc.common.ReflectionUtils;
import com.cxycds.irpc.protocol.Request;
import com.cxycds.irpc.protocol.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenglei on 2020/12/27.
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor,ServiceInstance> serviceMap;

    public ServiceManager(){
        this.serviceMap = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass,T bean){
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method m:methods) {
            ServiceInstance serviceInstance = new ServiceInstance(bean,m);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.build(interfaceClass,m);
            this.serviceMap.put(serviceDescriptor,serviceInstance);
            log.info("register service :{},{}",serviceDescriptor.getClazz(),serviceDescriptor.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor serviceDescriptor = request.getServiceDescriptor();
        return this.serviceMap.get(serviceDescriptor);
    }
}
