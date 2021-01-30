package com.cxycds.irpc.tranport;

/**
 * 1.启动，监听
 * 2.接受请求
 * 3.关闭监听
 * Created by chenglei on 2020/12/27.
 */
public interface TransportServer {
    void init(int port,RequestHandler requestHandler);

    void start();

    void stop();

}
