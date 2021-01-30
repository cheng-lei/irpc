package com.cxycds.irpc.codec.fastjson;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chenglei on 2020/12/27.
 */
public class FastJsonCoderTest {
    @Test
    public void decode() throws Exception {
        FastJsonCoder coder = new FastJsonCoder();
        com.cxycds.irpc.codec.fastjson.Test test = new com.cxycds.irpc.codec.fastjson.Test();
        test.setName("cxycds");
        test.setB(true);
        test.setAge(18);
        byte[] bytes = coder.encode(test);
        com.cxycds.irpc.codec.fastjson.Test result = coder.decode(bytes, com.cxycds.irpc.codec.fastjson.Test.class);
        Assert.assertEquals(test.getName(),result.getName());
        Assert.assertEquals(test.getAge(),result.getAge());
        Assert.assertEquals(test.getB(),result.getB());
    }

}