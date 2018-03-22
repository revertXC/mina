package com.deer.demo.codec;

import com.deer.demo.decoder.TestDecoder;
import com.deer.demo.encoder.TestEncoder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class TestCodecFactory implements ProtocolCodecFactory {

    private final TestDecoder decoder;
    private final TestEncoder encoder;
    //构造
    public TestCodecFactory() {
        encoder = new TestEncoder();
        decoder = new TestDecoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
