package com.cxycds.irpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by chenglei on 2020/12/27.
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
