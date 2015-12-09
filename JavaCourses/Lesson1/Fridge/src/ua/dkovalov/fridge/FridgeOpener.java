package ua.dkovalov.fridge;

import java.util.LinkedList;

public class FridgeOpener {
    public static final int MAX_RECURSION_LEVEL = 7;

    FridgeLock fridgeLock;
    LinkedList<LeverPosition> rotatedLevers = new LinkedList<LeverPosition>();

    public FridgeOpener() {  }

    public FridgeOpener(FridgeLock fridgeLock) {
        this.fridgeLock = fridgeLock;
    }

    public FridgeLock getFridgeLock() {
        return fridgeLock;
    }

    public LinkedList<LeverPosition> getRotatedLevers() {
        return rotatedLevers;
    }

    public int getIterations() {
        return rotatedLevers.size();
    }

    private boolean openRecursively(int maxRecursionDepth, int recursionLevel) {
        if (recursionLevel > maxRecursionDepth) {
            return false;
        } else if (fridgeLock.isUnlocked()) {
            return true;
        }

        for (int i = 0; i < fridgeLock.levers.length; i++) {
            for (int j = 0; j < fridgeLock.levers[i].length; j++) {
                LeverPosition leverPosition = new LeverPosition(i, j);
                if (rotatedLevers.size() == 0 || !rotatedLevers.getLast().equals(leverPosition)) {
                    rotatedLevers.add(leverPosition);
                    fridgeLock.rotateLever(leverPosition);
                    if (fridgeLock.isUnlocked()) {
                        return true;
                    } else if (openRecursively(maxRecursionDepth, recursionLevel + 1)) {
                        return true;
                    } else {
                        fridgeLock.rotateLever(leverPosition);
                        rotatedLevers.removeLast();
                    }
                }
            }
        }

        return false;
    }

    public void open() {
        for (int recursionLevel = 0; recursionLevel < MAX_RECURSION_LEVEL; recursionLevel++) {
            if (openRecursively(recursionLevel, 0)) {
                return;
            }
        }
        throw new RuntimeException("Unable to open the fridge with " + MAX_RECURSION_LEVEL + " levels of recursion allowed.");
    }

    public void generateRandomLock(int rotations) {
        byte[][] initialLevers = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        fridgeLock = new FridgeLock(initialLevers);

        for (int i = 0; i < rotations; i++) {
            int randomRow = (int) Math.floor(Math.random() * initialLevers.length);
            int randomColumn = (int) Math.floor(Math.random() * initialLevers[0].length);
            System.out.println("[" + randomRow + "; " + randomColumn + "]");
            fridgeLock.rotateLever(new LeverPosition(randomRow, randomColumn));
        }
    }
}
