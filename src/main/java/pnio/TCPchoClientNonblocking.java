package pnio;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by SunFlower on 2016/3/13.
 */
public class TCPchoClientNonblocking {
    public static void main(String[] args) throws Exception{
        String server = "localhost";
        byte[] sendByte = "Send from sever".getBytes();
        int port  = 9999;
        SocketChannel clntchan = SocketChannel.open();
        if(!clntchan.connect(new InetSocketAddress(server,port))){
            while (!clntchan.finishConnect()){
                System.out.print(".");
            }
        }
        System.out.println("\n");
        ByteBuffer writeBuf = ByteBuffer.wrap(sendByte);
        ByteBuffer readBuf  = ByteBuffer.allocate(sendByte.length);
        int totalBytesRcvd  = 0;
        int bytesRcvd;
        while (totalBytesRcvd < sendByte.length){
            if(writeBuf.hasRemaining()){
                clntchan.write(writeBuf);
            }
            if((bytesRcvd = clntchan.read(readBuf)) == -1){
                throw new SocketException("Connection closed prematurely");
            }
            totalBytesRcvd += bytesRcvd;
            System.out.print(".");
        }
        System.out.println("Received:"+ new String(readBuf.array(),0,totalBytesRcvd));
        clntchan.close();
    }
}
