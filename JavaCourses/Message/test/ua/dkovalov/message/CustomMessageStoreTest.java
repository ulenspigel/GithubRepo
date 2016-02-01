package ua.dkovalov.message;

import org.junit.Before;

import java.io.File;

public class CustomMessageStoreTest extends AbstractMessageStoreTest {
    @Before
    public void initMessageStore() {
        String path = "test-resources" + File.separator + "custom.dat";
        new File(path).delete();
        setMessageStore(new CustomMessageStore(path));
    }
}
