package ua.dkovalov.concurrentqueue;

public class ConcurrentQueue<E> {
    static final int CAPACITY = 10;
    private Object[] storage = new Object[CAPACITY];
    private int size;

    public synchronized void push(E element) throws InterruptedException {
        while (size == CAPACITY) {
            wait();
        }
        storage[size++] = element;
        notify();
    }

    @SuppressWarnings("unchecked")
    public synchronized E pop() throws InterruptedException {
        while (size == 0) {
            wait();
        }
        E element = (E) storage[0];
        System.arraycopy(storage, 1, storage, 0, --size);
        storage[size] = null;
        notify();
        return element;
    }
}
