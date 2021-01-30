package com.cxycds.irpc.demo;

import com.cxycds.irpc.server.RPCServer;
import com.cxycds.irpc.server.RPCServerConfig;

/**
 * Created by chenglei on 2020/12/27.
 */
public class Server {

    public static void main(String[] args) {
        RPCServer rpcServer = new RPCServer(new RPCServerConfig());
        rpcServer.register(HelloService.class,new HelloServiceImpl());
        rpcServer.start();
    }
}
