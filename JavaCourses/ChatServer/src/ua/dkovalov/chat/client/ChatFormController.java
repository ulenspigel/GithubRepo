package ua.dkovalov.chat.client;

import javafx.application.Platform;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ua.dkovalov.chat.IMessage;
import ua.dkovalov.chat.Message;

import java.util.List;

public class ChatFormController {
    private ChatClient chatClient;
    @FXML private TextArea messageInput;
    @FXML private ListView<String> messagesOutput;

    public ChatFormController() {
        chatClient = new ChatClient();
        chatClient.connect();
    }

    // TODO: replace with mechanism that waits for messages with wait() method and once they were received, displays them
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                List<IMessage> messages;
                while (true) {
                    messages = chatClient.receiveMessages();
                    for (IMessage message : messages) {
                        messagesOutput.getItems().add(message.toString());
                    }
                }
            }
        });
    }

    @FXML
    protected void handleSendButtonClick(ActionEvent event) {
        StringBuilder messageText = new StringBuilder();
        for (CharSequence messagePart : messageInput.getParagraphs()) {
            messageText.append(messagePart + "\n");
        }
        if (messageText.length() > 0) {
            chatClient.sendMessages(messageText.toString());
            messageInput.clear();
        }
    }
}
