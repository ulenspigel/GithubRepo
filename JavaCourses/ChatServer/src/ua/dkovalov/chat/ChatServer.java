package ua.dkovalov.chat;

public class ChatServer {
    public static void start() {
        ChatServerController controller = new ChatServerController();
        try {
            controller.open();
            new Thread(() -> controller.listenConnections()).start();
            controller.exchangeMessages();
        } finally {
            controller.close();
        }
    }

    public static void main(String[] args) {
        ChatServer.start();
    }
}
