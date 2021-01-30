package com.cxycds.irpc.client;

import com.cxycds.irpc.common.ReflectionUtils;
import com.cxycds.irpc.protocol.Peer;
import com.cxycds.irpc.tranport.TransportClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chenglei on 2020/12/27.
 */
public class RandomTransportSelector implements TransportSelector {
    List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peerList, int count, Class<? extends TransportClient> transportClientClass) {
        count = Math.max(count,1);
        for (Peer p:peerList) {
            for (int i = 0; i < count; i++) {
                TransportClient transportClient = ReflectionUtils.newInstance(transportClientClass);
                transportClient.connect(p);
                clients.add(transportClient);
            }
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(this.clients.size());
        return this.clients.remove(i);
    }

    @Override
    public  synchronized void release(TransportClient transportClient) {
        this.clients.add(transportClient);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client:clients) {
            client.close();
        }
        clients.clear();
    }
}
