package ua.dkovalov.datastructures.list;

public class LinkedListTest extends AbstractListTest {

    protected List instantiateList() {
        return new LinkedList();
    }

    protected List instantiateList(Object... objects) {
        LinkedList list = new LinkedList();
        for (Object object : objects) {
            list.add(object);
        }
        return list;
    }

}
