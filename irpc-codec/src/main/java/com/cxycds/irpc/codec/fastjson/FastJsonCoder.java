package com.cxycds.irpc.codec.fastjson;

import com.alibaba.fastjson.JSON;
import com.cxycds.irpc.codec.Coder;

/**
 * Created by chenglei on 2021/1/30.
 */
public class FastJsonCoder implements Coder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> tClass) {
        return JSON.parseObject(bytes,tClass);
    }

    @Override
    public byte[] encode(Object o) {
        return JSON.toJSONBytes(o);
    }
}
