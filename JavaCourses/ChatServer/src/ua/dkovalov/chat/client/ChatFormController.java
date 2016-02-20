package ua.dkovalov.chat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ua.dkovalov.chat.IMessage;
import java.util.List;

public class ChatFormController {
    private Chat chat;
    @FXML private TextArea messageInput;
    @FXML private ListView<String> messagesOutput;

    public ChatFormController() {
        chat = new Chat();
        chat.connect();
    }

    public void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    final List<IMessage> messages = chat.receiveMessages();
                    if (messages.size() > 0) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                for (IMessage message : messages) {
                                    messagesOutput.getItems().add(message.toString());
                                }
                            }
                        });
                    }
                }
            }
        }).start();
    }

    @FXML
    protected void handleSendButtonClick(ActionEvent event) {
        StringBuilder messageText = new StringBuilder();
        for (CharSequence messagePart : messageInput.getParagraphs()) {
            messageText.append(messagePart + "\n");
        }
        if (messageText.length() > 0) {
            chat.sendMessages(messageText.toString());
            messageInput.clear();
        }
    }
}
