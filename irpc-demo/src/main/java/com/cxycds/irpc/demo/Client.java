package com.cxycds.irpc.demo;

import com.cxycds.irpc.client.RPCClient;
import com.cxycds.irpc.client.RPCClientConfig;

/**
 * Created by chenglei on 2020/12/27.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        RPCClientConfig rpcClientConfig = new RPCClientConfig();
        RPCClient rpcClient = new RPCClient(rpcClientConfig);
        HelloService helloService = rpcClient.getProxy(HelloService.class);
        helloService.sayHello("cxycds");
        Thread.currentThread().join();
    }
}
