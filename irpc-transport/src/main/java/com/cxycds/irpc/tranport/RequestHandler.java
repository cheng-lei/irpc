package com.cxycds.irpc.tranport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求的handler
 * Created by chenglei on 2020/12/27.
 */
public interface RequestHandler {
    void onRequest(InputStream input, OutputStream output);
}
