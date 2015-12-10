package ua.dkovalov.datastructures.list;

public class ArrayListTest extends AbstractListTest {

    protected List instantiateList() {
        return new ArrayList();
    }

    protected List instantiateList(Object... objects) {
        ArrayList list = new ArrayList();
        for (Object object : objects) {
            list.add(object);
        }
        return list;
    }

}
