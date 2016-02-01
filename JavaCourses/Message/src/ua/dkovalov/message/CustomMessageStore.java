package ua.dkovalov.message;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class CustomMessageStore implements MessageStore {
    private static final char ITEMS_COUNT_MARKER = '>';
    private String filePath;

    public CustomMessageStore(String filePath) {
        this.filePath = filePath;
    }

    public CustomMessageStore() { }

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
        try (DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            outputStream.writeChar(ITEMS_COUNT_MARKER);
            outputStream.writeInt(list.size());
            outputStream.writeChar(ITEMS_COUNT_MARKER);
            for (Message message : list) {
                outputStream.writeInt(message.getId());
                outputStream.writeLong(message.getDate().getTime());
                outputStream.writeUTF(message.getContent());
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    @Override
    public ArrayList<Message> loadMessages() {
        ArrayList<Message> list = new ArrayList();
        try (DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            if (inputStream.readChar() != ITEMS_COUNT_MARKER) {
                throw new RuntimeException("Incorrect file format: no data about number of items");
            }
            int itemsCount = inputStream.readInt();
            if (inputStream.readChar() != ITEMS_COUNT_MARKER) {
                throw new RuntimeException("Incorrect file format: no data about number of items");
            }
            for (int i = 0; i < itemsCount; i++) {
                list.add(new Message(inputStream.readInt(), new Date(inputStream.readLong()), inputStream.readUTF()));
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
        return list;
    }
}
