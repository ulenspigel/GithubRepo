import java.util.LinkedList;

/**
 * Created by dgkovalev on 11/27/2015.
 */

public class FridgeLock {
    private static final byte LEVER_LOCKED = 1;

    byte[][] levers;

    public FridgeLock(byte[][] levers) {
        this.levers = levers;
    }

    @Override
    public String toString() {
        String leversString = new String("");

        for (byte[] leversRow : levers) {
            for (byte lever : leversRow)
                leversString += " " + (lever == LEVER_LOCKED ? '|' : '-');
            leversString += "\n";
        }

        return leversString;
    }

    public boolean isUnlocked() {
        for (byte[] leversRow : levers) {
            for (byte lever : leversRow) {
                if (lever == 1)
                    return false;
            }
        }
        return true;
    }

    public void rotateLocker(int row, int column) {
        for (int j = 0; j < levers[row].length; j++)
            levers[row][j] ^= LEVER_LOCKED;
        for (int i = 0; i < levers.length; i++)
            if (i != row)
                levers[i][column] ^= LEVER_LOCKED;
    }

    public LinkedList<LeverIndex> getLockedLevers() {
        LinkedList<LeverIndex> lockedLevers = new LinkedList<LeverIndex>();
        for (int i = 0; i < levers.length; i++)
            for (int j = 0; j < levers[i].length; j++)
                if (levers[i][j] == LEVER_LOCKED)
                    lockedLevers.add(new LeverIndex(i, j));

        return lockedLevers;
    }
}