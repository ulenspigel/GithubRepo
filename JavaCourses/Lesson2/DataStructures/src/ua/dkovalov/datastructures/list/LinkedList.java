package ua.dkovalov.datastructures.list;

public class LinkedList implements List {

    private Node first;
    private Node last;

    private int size = 0;

    public LinkedList() {
    }

    public void add(Object value) {
        if (size == 0) {
            Node node = new Node(value, null, null);
            first = node;
            last = node;
        } else {
            Node node = new Node(value, last, null);
            last.next = node;
            last = node;
        }
        size++;
    }

    public void add(int index, Object value) {
        if (index < 0 || index > size) {
            throw new RuntimeException("Index is out of array's bounds");
        }

        if (index == size) {
            add(value);
        } else {
            Node existingNode = getNode(index);
            Node insertedNode = new Node(value, existingNode.previous, existingNode);
            if (existingNode.previous == null) {
                first = insertedNode;
            } else {
                insertedNode.previous.next = insertedNode;
            }
            existingNode.previous = insertedNode;
            size++;
        }
    }

    private void checkIndexInBounds(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Index is out of array's bounds");
        }
    }

    private Node getNode(int index) {
        checkIndexInBounds(index);
        Node node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.previous;
            }
        }
        return node;
    }

    public Object get(int index) {
        return getNode(index).value;
    }

    public void set(int index, Object value) {
        getNode(index).value = value;
    }

    public Object remove(int index) {
        Node node = getNode(index);

        if (node.next == null) {
            last = node.previous;
        } else {
            node.next.previous = node.previous;
        }

        if (node.previous == null) {
            first = node.next;
        } else {
            node.previous.next = node.next;
        }

        size--;
        return node.value;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int indexOf(Object value) {
        Node node = first;
        int index = 0;

        while (node != null) {
            if (node.value.equals(value)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        Node node = last;
        int index = size - 1;

        while (node != null) {
            if (node.value.equals(value)) {
                return index;
            }
            node = node.previous;
            index--;
        }
        return -1;
    }

    public boolean contains(Object value) {
        return (indexOf(value) != -1);
    }

    private class Node {
        private Object value;
        private Node previous;
        private Node next;

        public Node(Object value, Node previous, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    // TODO: equals
    // TODO: toString
}
