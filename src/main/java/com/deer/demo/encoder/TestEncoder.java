package com.deer.demo.encoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class TestEncoder extends ProtocolEncoderAdapter {
//https://blog.csdn.net/c1481118216/article/details/52826250

    /**
     * 编码
     * @param ioSession
     * @param o
     * @param protocolEncoderOutput
     * @throws Exception
     */
    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        
    }

}
