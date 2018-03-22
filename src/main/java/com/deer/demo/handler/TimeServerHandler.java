package com.deer.demo.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * 处理器 必须都继承 IoHandler
 */
public class TimeServerHandler extends IoHandlerAdapter {

    /**
     * messageReceived和sessionIdle。在处理远程连接的正常过程中，应该始终在处理程序中定义异常。如果未定义此方法，则可能无法正确地报告异常。
     exceptioncatch方法将简单地打印错误的堆栈跟踪并关闭会话。对于大多数程序，这将是标准实践，除非处理程序可以从异常条件中恢复。
     messageReceived方法将从客户端接收数据并进行回写
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        String str = message.toString();
        if( str.trim().equalsIgnoreCase("quit") ) {
            session.close();
            return;
        }

        Date date = new Date();
        session.write( date.toString() );
        System.out.println("Message written..."+str);
    }

    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }
}
