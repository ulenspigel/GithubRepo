package ua.dkovalov.message;

import java.util.ArrayList;
import java.util.Collection;

public interface MessageStore {
    void persist(Message message);
    void persist(Collection<Message> list);
    ArrayList<Message> loadMessages();
}
