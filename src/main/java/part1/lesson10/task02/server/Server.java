package part1.lesson10.task02.server;

import java.io.IOException;

import static part1.lesson10.task02.server.ServerInfo.SERVER_PORT;

/**
 * Server
 *
 * @author Ekaterina Belolipetskaya
 */
public class Server {
    public static void main(String[] args) throws IOException {
        DatagramServerThread datagramServerThread = new DatagramServerThread(SERVER_PORT);
        datagramServerThread.start();
    }
}
