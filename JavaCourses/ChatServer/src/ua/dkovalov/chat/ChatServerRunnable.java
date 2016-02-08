package ua.dkovalov.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerRunnable implements Runnable {
    private static int PORT = 2305;
    ServerSocket serverSocket;
    private volatile List<ClientConnection> clients = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public ChatServerRunnable() {}

    public void start() throws InterruptedException, IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            new Thread(this).start();

            while (true) {
                // TODO: delete condition after debugging
                if (clients.size() > 0) {
                    checkClientsMessages();
                    notifyClients();
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } finally {
            serverSocket.close();
        }
        // TODO: finally - free resources for each socket
    }

    private synchronized void notifyClients() {
        if (messages.size() == 0) {
            return;
        }
        // TODO: check if socket was disconnected
        for (ClientConnection client : clients) {
            for (String message : messages) {
                client.output.println(">> " + message);
            }
        }
        messages.clear();
    }

    // TODO: turn method into synchronized to avoid adding new clients during the check
    private synchronized void checkClientsMessages() {
        // TODO: check if socket was disconnected
        String message;
        for (ClientConnection client : clients) {
            try {
                //while ((message = client.input.readLine()) != null) {
                if (client.socket.getInputStream().available() > 0) {
                    messages.add(client.input.readLine());
                }
                //}
            } catch (IOException ioe) {
                // TODO: add errors handling
                throw new RuntimeException(ioe);
            }
        }
    }

    private synchronized void addClientConnection(Socket socket) throws IOException {
        clients.add(new ClientConnection(socket));
    }

    @Override
    public void run() {
        try {
            while (true) {
                addClientConnection(serverSocket.accept());
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }


    public static void main(String... args) throws InterruptedException {
        new ChatServer().start();
    }

    private static class ClientConnection {
        static long clientId = 0;
        Socket socket;
        BufferedReader input;
        PrintWriter output;

        public ClientConnection(Socket socket) throws IOException {
            clientId++;
            this.socket = socket;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        }
    }
}
