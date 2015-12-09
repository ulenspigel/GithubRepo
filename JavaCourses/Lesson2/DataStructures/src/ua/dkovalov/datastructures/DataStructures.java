package ua.dkovalov.datastructures;

import ua.dkovalov.datastructures.list.*;

public class DataStructures {

    public static void main(String... args) {
        /*List list = new ArrayList(10);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(2);
        list.add(1);

        System.out.println(list.lastIndexOf(4));
        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "; ");
        }
        System.out.println();

        list.clear();
        System.out.println(list.size());
        System.out.println(list.isEmpty());

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "; ");
        }
        System.out.println();*/

        List list = new LinkedList();
        list.add(10);
        list.add(20);
        list.add(40);
        list.add(50);
        list.add(2, 30);
        list.add(0, 0);
        list.add(6, 60);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "; ");
        }
    }
}
