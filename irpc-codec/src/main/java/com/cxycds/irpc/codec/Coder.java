package com.cxycds.irpc.codec;

/**
 * Created by chenglei on 2021/1/30.
 */
public interface Coder {

    <T> T decode(byte[] bytes,Class<T> tClass);

    byte[] encode(Object o);
}
