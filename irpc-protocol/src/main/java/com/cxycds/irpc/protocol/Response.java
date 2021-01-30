package com.cxycds.irpc.protocol;

import lombok.Data;

/**
 * Created by chenglei on 2020/12/27.
 */
@Data
public class Response {
    //0成功，其他表示失败
    private int code = 0;
    private String message = "ok";
    private Object data;
}
