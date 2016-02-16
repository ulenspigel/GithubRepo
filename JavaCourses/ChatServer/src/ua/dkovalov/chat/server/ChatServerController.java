package ua.dkovalov.chat.server;

import ua.dkovalov.chat.Connection;
import ua.dkovalov.chat.IMessage;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerController<E extends IMessage> {
    private static int PORT = 2305;
    ServerSocket serverSocket;
    private volatile List<Connection<E>> clients = new ArrayList<>();
    // TODO: priority collection
    private List<E> messages = new ArrayList<>();

    public ChatServerController() {}

    public void open() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void close() {
        try {
            for (Connection<E> client : clients) {
                client.close();
            }
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
        for (Connection<E> client : clients) {
            if (!client.isDisconnected()) {
                client.sendMessages(messages);
            }
        }
        messages.clear();
    }

    synchronized void receiveMessages() {
        // TODO: check if socket was disconnected (functionality in Connection class)
        for (int i = 0; i < clients.size(); i++) {
            Connection<E> client = clients.get(i);
            if (client.isDisconnected()) {
                System.out.println("Before connection removing");
                clients.remove(i);
                System.out.println("After connection removing");
            } else {
                while (client.hasUnreadData()) {
                    messages.add(client.receiveMessage());
                }
            }
        }
    }

    public void listenConnections() {
        while (true) {
            try {
                addClientConnection(serverSocket.accept());
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }

    private synchronized void addClientConnection(Socket socket) throws IOException {
        clients.add(new Connection<>(socket));
    }
}
