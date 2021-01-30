package com.cxycds.irpc.tranport.http;

import com.cxycds.irpc.tranport.RequestHandler;
import com.cxycds.irpc.tranport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by chenglei on 2020/12/27.
 */
@Slf4j
public class HttpTransportServer implements TransportServer {
    private RequestHandler requestHandler;
    private Server server;

    @Override
    public void init(int port, RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.server = new Server(port);

        ServletContextHandler ctx = new ServletContextHandler();
        this.server.setHandler(ctx);

        ServletHolder servletHolder = new ServletHolder(new RequestServlet());
        ctx.addServlet(servletHolder, "/*");
    }

    @Override
    public void start() {
        try {
            this.server.start();
            this.server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try {
            this.server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info(req.getRemoteAddr() + " client connect:");
            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();
            requestHandler.onRequest(in, out);
            out.flush();
        }
    }
}
