package com.cxycds.irpc.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by chenglei on 2020/12/27.
 */
public class ReflectionUtilsTest {

    @Test
    public void newInstance() throws Exception {
        TestSon testSon = ReflectionUtils.newInstance(TestSon.class);
        Assert.assertNotNull(testSon);
    }

    @Test
    public void getPublicMethods() throws Exception {
        Method[] methods = ReflectionUtils.getPublicMethods(TestSon.class);
        Assert.assertEquals(2,methods.length);
        Assert.assertEquals("getSonName",methods[0].getName());

    }

    @Test
    public void invoke() throws Exception {
        Method[] methods = ReflectionUtils.getPublicMethods(TestSon.class);
        TestSon testSon = ReflectionUtils.newInstance(TestSon.class);
        Object result = ReflectionUtils.invoke(testSon,methods[1],"test");
        Assert.assertEquals("test",result);
    }

}