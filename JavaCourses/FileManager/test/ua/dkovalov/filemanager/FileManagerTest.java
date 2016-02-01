package ua.dkovalov.filemanager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Random;
import static ua.dkovalov.filemanager.FileManager.*;
import static org.junit.Assert.*;

public class FileManagerTest {
    private static final String ROOT = "test-resources" + File.separator + "test-dirs" + File.separator;
    /*
        test-resources
            test-dirs
                dummy
                    nestedA
                        text.txt
                    nestedB
                        nestedC
                            empty2.emp
                        empty1.emp
                    data.dat
     */
    @Before
    public void initialize() throws IOException {
        File rootDir = new File(ROOT);
        deleteItem(rootDir);
        String dirNestedAPath = ROOT + "dummy" + File.separator + "nestedA" + File.separator;
        new File(dirNestedAPath).mkdirs();
        try (DataOutputStream binaryFileWriter = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(
                ROOT + "dummy" + File.separator + "data.dat")))) {
            Random generator = new Random();
            for (int i = 0; i < 100; i++) {
                binaryFileWriter.writeDouble(generator.nextDouble());
            }
        }
        try (BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(dirNestedAPath + "text.txt"))) {
            for (int i = 0; i < 10; i++) {
                textFileWriter.write("Dummy line of text\n");
            }
        }
        String dirNestedBPath = ROOT + "dummy" + File.separator + "nestedB" + File.separator;
        new File(dirNestedBPath + "nestedC").mkdirs();
        new File(dirNestedBPath + "empty1.emp").createNewFile();
        new File(dirNestedBPath + "nestedC" + File.separator + "empty2.emp").createNewFile();
    }

    private static void deleteItem(File file) {
        if (file.isDirectory()) {
            for (File content: file.listFiles()) {
                deleteItem(content);
            }
        }
        file.delete();
    }

    @AfterClass
    public static void cleanup() throws IOException {
        deleteItem(new File(ROOT));
    }

    @Test
    public void testCalculateFiles() {
        assertEquals(calculateFiles(ROOT + "dummy"), 4);
    }

    @Test
    public void testCalculateDirectories() {
        assertEquals(calculateDirs(ROOT + "dummy"), 3);
    }

    @Test
    public void testCopy() {
        String source = ROOT + "dummy";
        String dest = ROOT + "dummy-copy1";
        assertTrue(copy(source, dest));
        assertEquals(calculateDirs(source), calculateDirs(dest));
        assertEquals(calculateFiles(source), calculateFiles(dest));

        source = ROOT + "dummy" + File.separator + "nestedB";
        dest = ROOT + "dummy-copy2" + File.separator + "nestedB-copy";
        assertTrue(copy(source, dest));
        assertEquals(calculateDirs(source), calculateDirs(dest));
        assertEquals(calculateFiles(source), calculateFiles(dest));

        source = ROOT + "dummy" + File.separator + "nestedA" + File.separator + "text.txt";
        dest = ROOT + "dummy-copy3" + File.separator + "text.txt";
        assertTrue(copy(source, dest));
        assertTrue(new File(dest).exists());
        assertTrue(new File(dest).isFile());

        assertFalse(copy(ROOT + "dummy" + File.separator + "nestedD", ROOT + "dummy-copy4"));
    }

    @Test
    public void testMove() {
        String source = ROOT + "dummy" + File.separator;
        int dummyFileCount = calculateFiles(source);
        String dest = ROOT + "dummy-copy1" + File.separator;
        assertTrue(move(source + "data.dat", dest + "data.dat"));
        assertEquals(calculateFiles(source), dummyFileCount - 1);
        assertEquals(calculateFiles(dest), 1);
        assertTrue(new File(dest + "data.dat").exists());
        assertTrue(new File(dest + "data.dat").isFile());

        source = ROOT + "dummy" + File.separator + "nestedB";
        int dummyDirCount = calculateDirs(ROOT + "dummy");
        int nestedBDirCount = calculateDirs(source);
        dest = ROOT + "dummy-copy2";
        assertTrue(move(source, dest));
        assertEquals(calculateDirs(ROOT + "dummy"), dummyDirCount - nestedBDirCount - 1);
        assertEquals(calculateDirs(dest), nestedBDirCount);
        assertTrue(new File(dest).exists());
        assertTrue(new File(dest).isDirectory());

        source = ROOT + "dummy" + File.separator + "nestedD";
        dest = ROOT + "dummy" + File.separator + "dummy-copy3";
        assertFalse(move(source, dest));
    }
}
