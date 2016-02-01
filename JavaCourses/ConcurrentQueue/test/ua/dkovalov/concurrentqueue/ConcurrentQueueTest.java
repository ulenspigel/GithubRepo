package ua.dkovalov.concurrentqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentQueueTest {
    ExecutorService executor;
    ConcurrentQueue<QueueElement> queue;
    private static final int THREAD_IDDLE_TIME = 3;

    @Before
    public void initQueue() throws InterruptedException {
        queue = new ConcurrentQueue<>();
        for (int i = 0; i < ConcurrentQueue.CAPACITY; i++) {
            queue.push(new QueueElement(Thread.currentThread().getId(), i, "Queue element #" + i));
        }
        executor = Executors.newSingleThreadExecutor();
    }

    @After
    public void cleanup() {
        executor.shutdown();
    }

    @Test
    public void testPush() throws InterruptedException {
        long startTime = System.nanoTime();
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(THREAD_IDDLE_TIME);
                queue.pop();
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
        });
        queue.push(new QueueElement(Thread.currentThread().getId(), ConcurrentQueue.CAPACITY, "Queue element #" + ConcurrentQueue.CAPACITY));
        long endTime = System.nanoTime();
        assertTrue(Math.round((endTime - startTime) / 1_000_000_000) >= THREAD_IDDLE_TIME);
    }

    @Test
    public void testPop() throws InterruptedException {
        QueueElement element = null;
        for (int i = 0; i < ConcurrentQueue.CAPACITY; i++) {
            element = queue.pop();
        }
        assertEquals(element.getElementNumber(), ConcurrentQueue.CAPACITY - 1);
        assertEquals(element.getThreadID(), Thread.currentThread().getId());
        long startTime = System.nanoTime();
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(THREAD_IDDLE_TIME);
                queue.push(new QueueElement(Thread.currentThread().getId(), ConcurrentQueue.CAPACITY, "Queue element #" + ConcurrentQueue.CAPACITY));
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
        });
        element = queue.pop();
        long endTime = System.nanoTime();
        assertTrue(Math.round((endTime - startTime) / 1_000_000_000) >= THREAD_IDDLE_TIME);
        assertEquals(element.getElementNumber(), ConcurrentQueue.CAPACITY);
        assertNotEquals(element.getThreadID(), Thread.currentThread().getId());
    }

    private static class QueueElement {
        private long threadID;
        private int elementNumber;
        private String payload;

        public QueueElement(long threadID, int elementNumber, String payload) {
            this.threadID = threadID;
            this.elementNumber = elementNumber;
            this.payload = payload;
        }

        public long getThreadID() {
            return threadID;
        }

        public int getElementNumber() {
            return  elementNumber;
        }

        public String getPayload() {
            return payload;
        }

        @Override
        public String toString() {
            return "QueueElement{" +
                    "threadID='" + threadID + '\'' +
                    ", element#='" + elementNumber + '\'' +
                    ", payload='" + payload + '\'' +
                    '}';
        }
    }
}
