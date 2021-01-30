package com.cxycds.irpc.server;

import com.cxycds.irpc.common.ReflectionUtils;
import com.cxycds.irpc.protocol.Request;
import com.cxycds.irpc.protocol.ServiceDescriptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by chenglei on 2020/12/27.
 */
public class ServiceManagerTest {
    ServiceManager serviceManager;

    @Before
    public void before(){
        serviceManager = new ServiceManager();
        TestBean testBean = new TestBean();
        serviceManager.register(TestInterface.class,testBean);
    }
    @Test
    public void register() throws Exception {
        TestBean testBean = new TestBean();
        serviceManager.register(TestInterface.class,testBean);
    }

    @Test
    public void lookup() throws Exception {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.build(TestInterface.class,method);
        Request request = new Request();
        request.setServiceDescriptor(serviceDescriptor);
        ServiceInstance serviceInstance = serviceManager.lookup(request);
        Assert.assertNotNull(serviceInstance);
    }

}