package com.cxycds.irpc.server;

import com.cxycds.irpc.common.ReflectionUtils;
import com.cxycds.irpc.protocol.Request;

/**
 * Created by chenglei on 2020/12/27.
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance serviceInstance, Request request){
        return ReflectionUtils.invoke(serviceInstance.getTarget(),serviceInstance.getMethod(),request.getParameters());
    }
}
