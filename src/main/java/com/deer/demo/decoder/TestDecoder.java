package com.deer.demo.decoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class TestDecoder extends CumulativeProtocolDecoder {

    /**
     * 解码
     * @param ioSession
     * @param ioBuffer
     * @param protocolDecoderOutput
     * @return
     * @throws Exception
     */
    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        int headerLen = 10;
        if(ioBuffer.remaining() > headerLen){ //如果 IOBuffer 中有数据
            ioBuffer.mark();//标记当前position，以便后继的reset操作能恢复position位置
            //获取数据包长度
            int bodyLen = 20;
            System.out.println("TestEncoder（解码）长度为:"+bodyLen);
            if(ioBuffer.remaining() <bodyLen - headerLen) {
                //内容不够， 重置position到操作前，进行下一轮接受新数据
                ioBuffer.reset();
                return false;
            }else{
                //内容足够
                ioBuffer.reset(); //重置回复position位置到操作前
                byte[] packArray = new byte[bodyLen];
                ioBuffer.get(packArray, 0, bodyLen); //获取整条报文

                //根据自己需要解析接收到的东西  我的例子 把收到的报文转成String
                String str = new String(packArray);
                protocolDecoderOutput.write(str); //发送出去 就算完成了

                if(ioBuffer.remaining() > 0){//如果读取一个完整包内容后还粘了包，就让父类再调用一次，进行下一次解析
                    return true;
                }
            }
        }
        return false;
    }
}
