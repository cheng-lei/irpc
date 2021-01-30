package com.cxycds.irpc.server;

import com.cxycds.irpc.codec.Coder;
import com.cxycds.irpc.codec.fastjson.FastJsonCoder;
import com.cxycds.irpc.tranport.TransportClient;
import com.cxycds.irpc.tranport.TransportServer;
import com.cxycds.irpc.tranport.http.HttpTransportClient;
import com.cxycds.irpc.tranport.http.HttpTransportServer;
import lombok.Data;

/**
 * Created by chenglei on 2020/12/27.
 */
@Data
public final class RPCServerConfig {
    private Class<? extends Coder> coderClass = FastJsonCoder.class;
    private Class<? extends TransportClient> transportClientClass = HttpTransportClient.class;
    private Class<? extends TransportServer> transportServerClass = HttpTransportServer.class;
    private int port =8888;
}
