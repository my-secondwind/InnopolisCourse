package part1.lesson10.task01.server;

import java.io.IOException;

import static part1.lesson10.task01.server.ServerInfo.SERVER_PORT;

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
