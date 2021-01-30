package com.cxycds.irpc.server;

/**
 * Created by chenglei on 2020/12/27.
 */
public class TestBean implements TestInterface {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }
}
