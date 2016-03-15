package pnio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * Created by SunFlower on 2016/3/13.
 */
public interface TCPProtocol {
    void handleAccept(SelectionKey key) throws IOException;
    void handleRead(SelectionKey key) throws IOException;
    void handleWrite(SelectionKey key) throws IOException;
}
