package part1.lesson10.task02.client;

import java.io.IOException;

import static part1.lesson10.task02.client.ClientsInfo.CLIENTS_NAME;
import static part1.lesson10.task02.client.ClientsInfo.CLIENTS_PORTS;

/**
 * Client
 *
 * @author Ekaterina Belolipetskaya
 */
public class Client {
    public static void main(String[] args) throws IOException {
        ClientThread client = new ClientThread(CLIENTS_PORTS[0], CLIENTS_NAME[0]);
        client.start();

    }
}
