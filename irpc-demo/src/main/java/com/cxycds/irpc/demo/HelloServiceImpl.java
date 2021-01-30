package com.cxycds.irpc.demo;

/**
 * Created by chenglei on 2020/12/27.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hi :"+name+",你好啊！欢迎使用程序猿程大叔的irpc";
    }
}
