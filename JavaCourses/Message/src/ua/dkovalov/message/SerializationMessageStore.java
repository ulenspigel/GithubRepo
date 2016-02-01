package ua.dkovalov.message;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SerializationMessageStore implements MessageStore {
    private String filePath;

    public SerializationMessageStore(String filePath) {
        this.filePath = filePath;
    }

    public SerializationMessageStore() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void persist(Message message) {
        persist(Arrays.asList(message));
    }

    @Override
    public void persist(Collection<Message> list) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            for (Message message : list) {
                outputStream.writeObject(message);
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    @Override
    public ArrayList<Message> loadMessages() {
        ArrayList<Message> list = new ArrayList();
        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            try {
                while (true) {
                    list.add((Message) inputStream.readObject());
                }
            } catch (EOFException eofe) { }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
}
