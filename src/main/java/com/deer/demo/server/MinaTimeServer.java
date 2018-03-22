package com.deer.demo.server;

import com.deer.demo.codec.TestCodecFactory;
import com.deer.demo.handler.TimeServerHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 服务端
 */
public class MinaTimeServer {
    private static final int PORT = 9123;

    public static void main(String[] args) throws IOException {
        /**
         * 首先，我们需要一个对象来监听传入的连接。由于这个程序将是基于TCP/IP的，我们将为我们的程序添加一个SocketAcceptor。
         */
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
        acceptor.getFilterChain().addLast( "codec",
                new ProtocolCodecFilter(
                        new TestCodecFactory()
                )
        );
        acceptor.setHandler(  new TimeServerHandler() );

        /**
         * 会话设置了设置IoHandler、输入缓冲区大小和空闲属性
         */
        acceptor.getSessionConfig().setReadBufferSize( 1000 );
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
        acceptor.bind( new InetSocketAddress(PORT) );
        System.out.println("telnet 127.0.0.1 9123");
    }
}
