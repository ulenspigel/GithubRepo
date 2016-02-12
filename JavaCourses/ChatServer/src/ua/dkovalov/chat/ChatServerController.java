package ua.dkovalov.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerController {
    private static int PORT = 2305;
    ServerSocket serverSocket;
    private volatile List<Connection> clients = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public ChatServerController() {}

    public void open() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void close() {
        // TODO: finalize all the connections & dispose their io streams
        try {
            serverSocket.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void exchangeMessages() {
        while (true) {
            if (clients.size() > 0) {
                receiveMessages();
                sendMessages();
            }
        }
    }

    synchronized void sendMessages() {
        if (messages.size() == 0) {
            return;
        }
        // TODO: check if socket was disconnected (functionality in Connection class)
        for (Connection client : clients) {
            client.sendMessages(messages);
        }
        messages.clear();
    }

    synchronized void receiveMessages() {
        // TODO: check if socket was disconnected (functionality in Connection class)
        for (Connection client : clients) {
            while (client.hasUnreadData()) {
                messages.add(client.receiveMessage());
            }
        }
    }

    public void listenConnections() {
        while (true) {
            try {
                System.out.println("Before acceptance");
                addClientConnection(serverSocket.accept());
                System.out.println("Connection accepted");
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }

    private synchronized void addClientConnection(Socket socket) throws IOException {
        clients.add(new Connection(socket));
    }
}
