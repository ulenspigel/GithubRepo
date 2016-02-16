package ua.dkovalov.chat.client;

import ua.dkovalov.chat.Connection;
import ua.dkovalov.chat.IMessage;
import ua.dkovalov.chat.Message;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatClient {
    private static int SERVER_PORT = 2305;
    private static String SERVER_ADDRESS = "127.0.0.1";
    private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public Connection<Message> connection;
    // TODO: replace with real input
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public ChatClient() {
    }

    public void connect() {
        try {
            connection = new Connection<>(new Socket(SERVER_ADDRESS, SERVER_PORT));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void disconnect() {
        connection.close();
    }

    public void sendMessages(String message) {
        connection.sendMessage(new Message(connection.getLocalConnectionID(), Calendar.getInstance(), message));
    }

    public List<IMessage> receiveMessages() {
        List<IMessage> messages = new ArrayList<>();
        while (connection.hasUnreadData()) {
            messages.add(connection.receiveMessage());
        }
        return messages;
    }

    /*private void displayMessage(Message message) {
        if (!message.getSenderID().equals(connection.getLocalConnectionID())) {
            String formattedMessage = "[" + DATE_FORMATTER.format(message.getDate().getTime()) + "] " +
                    message.getSenderID() + ": " + message.getContent();
            System.out.println(formattedMessage);
        }
    }*/

    /*public static void main(String[] args) {
        ChatClientController client = new ChatClientController();
        client.connect();
        while (true) {
            client.sendMessages();
            client.receiveMessages();
        }
    }*/
}
