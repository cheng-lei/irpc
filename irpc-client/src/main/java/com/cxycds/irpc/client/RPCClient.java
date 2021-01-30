package com.cxycds.irpc.client;

import com.alibaba.fastjson.JSON;
import com.cxycds.irpc.codec.Coder;
import com.cxycds.irpc.common.ReflectionUtils;
import com.cxycds.irpc.protocol.Request;
import com.cxycds.irpc.protocol.Response;
import com.cxycds.irpc.protocol.ServiceDescriptor;
import com.cxycds.irpc.tranport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chenglei on 2020/12/27.
 */
@Slf4j
public class RPCClient {
    private RPCClientConfig config;
    private Coder coder;
    private TransportSelector transportSelector;

    public RPCClient(RPCClientConfig config) {
        this.config = config;
        this.coder = ReflectionUtils.newInstance(config.getCoderClass());
        this.transportSelector = ReflectionUtils.newInstance(config.getTranportSelectorClass());
        this.transportSelector.init(config.getPeers(), config.getConnectCount(), config.getTransportClientClass());
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Request request = new Request();
                ServiceDescriptor serviceDescriptor = ServiceDescriptor.build(clazz, method);
                request.setServiceDescriptor(serviceDescriptor);
                request.setParameters(args);
                byte[] bytes = coder.encode(request);
                TransportClient transportClient = null;
                try {
                    transportClient = transportSelector.select();
                    InputStream inputStream = transportClient.write(new ByteArrayInputStream(bytes));
                    byte[] resultBytes = IOUtils.readFully(inputStream, inputStream.available());
                    Response response = coder.decode(resultBytes, Response.class);
                    if (response.getCode() == 0) {
                        log.info("remote invoke success,result:" + response.getData());
                        return response.getData();
                    } else {
                        log.error("remote invoke failed,response:" + JSON.toJSONString(response));
                        throw new IllegalStateException(response.getCode() + ":" + response.getMessage());
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new IllegalStateException(e.getMessage());
                } finally {
                    if (transportClient != null) {
                        transportSelector.release(transportClient);
                    }
                }
            }
        });
    }
}
