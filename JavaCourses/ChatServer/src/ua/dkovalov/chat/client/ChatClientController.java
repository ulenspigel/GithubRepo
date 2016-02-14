package ua.dkovalov.chat.client;

import ua.dkovalov.chat.Connection;
import ua.dkovalov.chat.Message;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatClientController {
    private static int SERVER_PORT = 2305;
    private static String SERVER_ADDRESS = "127.0.0.1";
    private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Connection<Message> connection;
    // TODO: replace with real input
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public ChatClientController() {}

    public void connect() {
        try {
            connection = new Connection<>(new Socket(SERVER_ADDRESS, SERVER_PORT));
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
            displayMessage(connection.receiveMessage());
        }
    }

    private void displayMessage(Message message) {
        if (!message.getSenderID().equals(connection.getLocalConnectionID())) {
            String formattedMessage = "[" + DATE_FORMATTER.format(message.getDate().getTime()) + "] " +
                    message.getSenderID() + ": " + message.getContent();
            System.out.println(formattedMessage);
        }
    }

    public static void main(String[] args) {
        ChatClientController client = new ChatClientController();
        client.connect();
        while (true) {
            client.sendMessages();
            client.receiveMessages();
        }
    }
}
