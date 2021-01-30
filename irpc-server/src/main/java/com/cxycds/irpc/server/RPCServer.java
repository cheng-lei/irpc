package com.cxycds.irpc.server;

import com.cxycds.irpc.codec.Coder;
import com.cxycds.irpc.common.ReflectionUtils;
import com.cxycds.irpc.protocol.Request;
import com.cxycds.irpc.protocol.Response;
import com.cxycds.irpc.tranport.RequestHandler;
import com.cxycds.irpc.tranport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by chenglei on 2020/12/27.
 */
@Slf4j
public class RPCServer {
    private RPCServerConfig config;
    private TransportServer net;
    private Coder coder;
    private ServiceInvoker serviceInvoker;
    private ServiceManager serviceManager;

    public RPCServer(RPCServerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportServerClass());
        this.coder = ReflectionUtils.newInstance(config.getCoderClass());
        this.serviceInvoker = new ServiceInvoker();
        this.serviceManager = new ServiceManager();
        this.net.init(config.getPort(), new RequestHandler(){

            @Override
            public void onRequest(InputStream input, OutputStream output) {
                Response response = new Response();
                try {
                    byte[] inbytes = IOUtils.readFully(input,input.available());
                    Request request = coder.decode(inbytes,Request.class);
                    ServiceInstance serviceInstance = serviceManager.lookup(request);
                    Object result = serviceInvoker.invoke(serviceInstance,request);
                    response.setData(result);
                } catch (Exception e) {
                    log.error(e.getMessage(),e);
                    response.setCode(1);
                    response.setMessage("request error:"+e.getClass().getName()+","+e.getMessage());
                }finally {
                    byte[] bytes = coder.encode(response);
                    try {
                        output.write(bytes);
                    } catch (IOException e) {
                        log.error(e.getMessage(),e);
                    }
                }
            }
        });
    }

    public <T> void register(Class<T> interfaceClass,T bean){
        this.serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }
}
