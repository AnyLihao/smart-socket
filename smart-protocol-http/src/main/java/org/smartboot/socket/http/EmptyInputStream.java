/*
 * Copyright (c) 2018, org.smartboot. All rights reserved.
 * project name: smart-socket
 * file name: EmptyInputStream.java
 * Date: 2018-02-03
 * Author: sandao
 */

package org.smartboot.socket.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 三刀
 * @version V1.0 , 2018/2/3
 */
class EmptyInputStream extends InputStream {
    @Override
    public int read() throws IOException {
        return -1;
    }
}
