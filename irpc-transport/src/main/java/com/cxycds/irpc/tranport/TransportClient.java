package com.cxycds.irpc.tranport;

import com.cxycds.irpc.protocol.Peer;

import java.io.InputStream;

/**
 * 1.创建链接
 * 2.发送数据，等待响应
 * 3.关闭链接
 * Created by chenglei on 2020/12/27.
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
