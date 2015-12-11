package ua.dkovalov.datastructures.list;

public class ArrayList implements List {

    private static final int INIT_ARRAY_SIZE = 5;
    private static final int ARRAY_INCREASE_BY = 5;

    private Object[] arrayStorage;
    private int size = 0;

    public ArrayList() {
        arrayStorage = new Object[INIT_ARRAY_SIZE];
    }

    public ArrayList(int initSize) {
        arrayStorage = new Object[initSize];
    }

    private void extend() {
        Object[] extendedArray = new Object[arrayStorage.length + ARRAY_INCREASE_BY];
        System.arraycopy(arrayStorage, 0, extendedArray, 0, arrayStorage.length);
        arrayStorage = extendedArray;
    }

    private void checkIndexInBounds(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Index is out of array's bounds");
        }
    }

    public void add(Object value) {
        if (arrayStorage.length == size) {
            extend();
        }
        arrayStorage[size++] = value;
    }

    public void add(int index, Object value) {
        if (index < 0 || index > size) {
            throw new RuntimeException("Index is out of array's bounds");
        }

        if (arrayStorage.length == size) {
            extend();
        }

        if (index != size) {
            System.arraycopy(arrayStorage, index, arrayStorage, index + 1, size - index);
        }
        arrayStorage[index] = value;
        size++;
    }

    public Object get(int index) {
        checkIndexInBounds(index);
        return arrayStorage[index];
    }

    public void set(int index, Object value) {
        checkIndexInBounds(index);
        arrayStorage[index] = value;
    }

    public Object remove(int index) {
        Object removedObject = get(index);
        System.arraycopy(arrayStorage, index + 1, arrayStorage, index, size - index - 1);
        size--;
        return removedObject;
    }

    public void clear() {
        arrayStorage = new Object[INIT_ARRAY_SIZE];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (arrayStorage[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; i--) {
            if (arrayStorage[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object value) {
        return (indexOf(value) != -1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            stringBuilder.append((i == 0 ? "" : ";") + arrayStorage[i]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
