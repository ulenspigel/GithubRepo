package ua.dkovalov.datastructures.list;

public interface List {
    void add(Object value);
    void add(int index, Object value);
    Object get(int index);
    void set(int index, Object value);
    Object remove(int index);
    void clear();
    int size();
    boolean isEmpty();
    int indexOf(Object value);
    int lastIndexOf(Object value);
    boolean contains(Object value);
}
