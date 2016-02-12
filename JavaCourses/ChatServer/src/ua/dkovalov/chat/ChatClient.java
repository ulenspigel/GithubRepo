package ua.dkovalov.chat;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;

public class ChatClient {
    private static int SERVER_PORT = 2305;
    private static String SERVER_ADDRESS = "127.0.0.1";
    private Connection connection;
    // TODO: replace with real input
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public ChatClient() {}

    public void connect() {
        try {
            connection = new Connection(new Socket(SERVER_ADDRESS, SERVER_PORT));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void sendMessages() {
        try {
            while (System.in.available() > 0) {
                connection.sendMessage(new Message(connection.getLocalConnectionID(), Calendar.getInstance(), input.readLine()));
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void receiveMessages() {
        while (connection.hasUnreadData()) {
            System.out.println(">> " + connection.receiveMessage().getContent());
        }
    }

    public static void main(String[] args) {
        System.out.println("Client started");
        ChatClient client = new ChatClient();
        System.out.println("Before client connection");
        client.connect();
        System.out.println("Client connected");
        while (true) {
            client.sendMessages();
            client.receiveMessages();
        }
    }
}
