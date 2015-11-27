import java.util.LinkedList;
import java.util.List;

/**
 * Created by dgkovalev on 11/27/2015.
 */
public class FridgeOpener {
    public static final int MAX_ROTATIONS_COUNT = 100;

    FridgeLock fridgeLock;
    List<LeverIndex> rotatedLevers = new LinkedList<LeverIndex>();

    public FridgeOpener() {  }

    public FridgeOpener(FridgeLock fridgeLock) {
        this.fridgeLock = fridgeLock;
    }

    public FridgeLock getFridgeLock() {
        return fridgeLock;
    }

    public List<LeverIndex> getRotatedLevers() {
        return rotatedLevers;
    }

    public int getIterations() {
        return rotatedLevers.size();
    }

    public void open() throws Exception {
        List<LeverIndex> lockedLevers = fridgeLock.getLockedLevers();
        while ((lockedLevers = fridgeLock.getLockedLevers()).size() > 0) {
            for (LeverIndex leverIndex : lockedLevers) {
                fridgeLock.rotateLocker(leverIndex.row, leverIndex.column);
                rotatedLevers.add(leverIndex);
                if (fridgeLock.isUnlocked())
                    return;
                else if (rotatedLevers.size() == MAX_ROTATIONS_COUNT)
                    throw new Exception("Unable to solve the task in an acceptable number of steps.");
            }
        }
    }

    public void generateRandomLock() {
        byte[][] initialLevers = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        fridgeLock = new FridgeLock(initialLevers);

        for (int i = 0; i < Math.random() * 10; i++) {
            fridgeLock.rotateLocker((int) Math.floor(Math.random() * initialLevers.length),
                    (int) Math.floor(Math.random() * initialLevers[0].length));
        }
    }
}
