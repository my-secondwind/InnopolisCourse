package part1.lesson10.task02.client;

import java.io.IOException;

import static part1.lesson10.task02.client.ClientsInfo.CLIENTS_NAME;
import static part1.lesson10.task02.client.ClientsInfo.CLIENTS_PORTS;

/**
 * Client2
 *
 * @author Ekaterina Belolipetskaya
 */
public class Client2 {
    public static void main(String[] args) throws IOException {
        ClientThread client = new ClientThread(CLIENTS_PORTS[1], CLIENTS_NAME[1]);
        client.start();
    }
}
