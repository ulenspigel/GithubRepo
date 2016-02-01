package ua.dkovalov.message;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public abstract class AbstractMessageStoreTest {
    private MessageStore messageStore;

    public void setMessageStore(MessageStore messageStore) {
        this.messageStore = messageStore;
    }
    
    @Test
    public void testPersistingMessage() {
        Message message = new Message(123, new Date(), "Message payload");
        messageStore.persist(message);
        ArrayList<Message> loadedMessages = messageStore.loadMessages();
        assertEquals(loadedMessages.size(), 1);
        assertTrue(message.equals(loadedMessages.get(0)));
    }

    @Test
    public void testPersistingMessages() {
        ArrayList<Message> messages = new ArrayList();
        for (int i = 100; i < 110; i++) {
            messages.add(new Message(100 + i, new Date(), "Message with ID=" + (100 + i)));
        }
        messageStore.persist(messages);
        ArrayList<Message> loadedMessages = messageStore.loadMessages();
        assertEquals(messages.size(), loadedMessages.size());
        for (int i = 0; i < messages.size(); i++) {
            assertTrue(messages.get(i).equals(loadedMessages.get(i)));
        }
    }

}
