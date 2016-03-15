package pnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by SunFlower on 2016/3/13.
 */
public class TCPServerSeletor {
    private static final int BUFSIZE = 256;
    private static final int TIMEOUT  = 3000;
    public static void main(String[] args) throws IOException{
        Selector selector  = Selector.open();
        ServerSocketChannel listenChannel = ServerSocketChannel.open();
        listenChannel.socket().bind(new InetSocketAddress(9999));
        listenChannel.configureBlocking(false);
        listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE);
        while (true){
            if(selector.select(TIMEOUT) == 0){
                System.out.print(".");
                continue;
            }
            Iterator<SelectionKey> keyIterator  = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    protocol.handleAccept(key);
                }
                if(key.isReadable()){
                    protocol.handleRead(key);
                }
                if(key.isValid() && key.isWritable()){
                    protocol.handleWrite(key);
                }
                keyIterator.remove();
            }
        }
    }
}
