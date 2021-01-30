package com.cxycds.irpc.client;

import com.cxycds.irpc.protocol.Peer;
import com.cxycds.irpc.tranport.TransportClient;

import java.util.List;

/**
 * Created by chenglei on 2020/12/27.
 */
public interface TransportSelector {

    void init(List<Peer> peerList,int count,Class<? extends TransportClient> transportClientClass);

    TransportClient select();

    void release(TransportClient transportClient);

    void close();

}
