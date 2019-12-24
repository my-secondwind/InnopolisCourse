package part1.lesson10.task01.client;

import java.io.IOException;
import java.net.InetAddress;

import static part1.lesson10.task01.client.ClientsInfo.CLIENTS_NAME;
import static part1.lesson10.task01.client.ClientsInfo.CLIENTS_PORTS;
import static part1.lesson10.task01.server.ServerInfo.SERVER_ADDRESS;
import static part1.lesson10.task01.server.ServerInfo.SERVER_PORT;

/**
 * DatagramClient2
 *
 * @author Ekaterina Belolipetskaya
 */
public class DatagramClient2 {
    public static void main(String[] args) throws IOException {
        new DatagramClientThread(CLIENTS_PORTS[1], CLIENTS_NAME[1], InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT).start();
    }
}
