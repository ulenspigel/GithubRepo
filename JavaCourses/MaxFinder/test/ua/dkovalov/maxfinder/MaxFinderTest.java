package ua.dkovalov.maxfinder;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static ua.dkovalov.maxfinder.MaxFinder.*;

public class MaxFinderTest {
    private final static int NUM_OF_WORKERS = 2;

    @Test
    public void testCalculate() {
        List<Integer> list = new ArrayList<>();
        assertNull(calculate(list));
        list = Arrays.asList(100);
        assertEquals(calculate(list), Integer.valueOf(100));
        list = new ArrayList<>(ITEMS_PER_WORKER * NUM_OF_WORKERS);
        Random random = new Random();
        for (int i = 0; i < ITEMS_PER_WORKER * NUM_OF_WORKERS; i++) {
            list.add(random.nextInt(100));
        }
        int maxIndex = random.nextInt(list.size());
        list.set(maxIndex, list.get(maxIndex) + 1000);
        assertEquals(calculate(list), list.get(maxIndex));
    }
}
