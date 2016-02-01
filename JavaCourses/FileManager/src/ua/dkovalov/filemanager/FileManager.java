package ua.dkovalov.filemanager;

import java.io.*;

public class FileManager {

    private enum ItemType {DIRECTORY, FILE};

    public static int calculateFiles(String path) {
        return calculateDirectoryItems(path, ItemType.FILE);
    }

    public static int calculateDirs(String path) {
        return calculateDirectoryItems(path, ItemType.DIRECTORY);
    }

    private static int calculateDirectoryItems(String path, ItemType itemType) {
        File[] directoryContent = (new File(path)).listFiles();
        if (directoryContent == null) {
            return 0;
        }

        int itemCount = 0;
        for (File contentItem : directoryContent) {
            if (!contentItem.isHidden()) {
                if (contentItem.isDirectory()) {
                    if (itemType == ItemType.DIRECTORY) {
                        itemCount++;
                    }
                    itemCount += calculateDirectoryItems(contentItem.getAbsolutePath(), itemType);
                } else if (itemType == ItemType.FILE) {
                    itemCount++;
                }
            }
        }

        return itemCount;
    }

    private static boolean transfer(String from, String to, boolean deleteSource) {
        boolean transferStatus = true;
        File source = new File(from);

        if (!source.exists()) {
            return false;
        }

        if (source.isDirectory()) {
            new File(to).mkdirs();
            File[] directoryItems = source.listFiles();
            if (directoryItems != null) {
                for (File directoryItem : source.listFiles()) {
                    transferStatus &= transfer(directoryItem.getAbsolutePath(), to + File.separator + directoryItem.getName(), deleteSource);
                }
            }
        } else {
            new File(to.substring(0, to.lastIndexOf(File.separator))).mkdirs();
            transferStatus = copyFile(from, to);
        }

        if (deleteSource) {
            transferStatus &= source.delete();
        }
        return transferStatus;
    }

    private static boolean copyFile(String from, String to) {
        try (
                BufferedInputStream input = new BufferedInputStream(new FileInputStream(from));
                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(to));
        ) {
            byte[] buffer = new byte[8192];
            while (input.read(buffer) > 0) {
                output.write(buffer);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean copy(String from, String to) {
        return transfer(from, to, false);
    }

    public static boolean move(String from, String to) {
        return transfer(from, to, true);
    }
}
