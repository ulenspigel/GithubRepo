package ua.dkovalov.message;

import org.junit.AfterClass;
import org.junit.Before;
import java.io.File;

public class SerializationMessageStoreTest extends AbstractMessageStoreTest {
    @Before
    public void initMessageStore() {
        String path = "test-resources" + File.separator + "serialized.dat";
        new File(path).delete();
        setMessageStore(new SerializationMessageStore(path));
    }
}
