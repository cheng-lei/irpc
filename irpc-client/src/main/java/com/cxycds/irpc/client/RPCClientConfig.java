package com.cxycds.irpc.client;

import com.cxycds.irpc.codec.Coder;
import com.cxycds.irpc.codec.fastjson.FastJsonCoder;
import com.cxycds.irpc.protocol.Peer;
import com.cxycds.irpc.tranport.TransportClient;
import com.cxycds.irpc.tranport.http.HttpTransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chenglei on 2020/12/27.
 */
@Data
public class RPCClientConfig {
    private Class<? extends TransportClient> transportClientClass = HttpTransportClient.class;
    private Class<?extends Coder> coderClass = FastJsonCoder.class;
    private Class<?extends TransportSelector> tranportSelectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> peers = Arrays.asList(
            new Peer("127.0.0.1",8888)
    );

}
