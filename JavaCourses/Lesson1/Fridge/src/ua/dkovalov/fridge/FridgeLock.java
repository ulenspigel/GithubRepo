package ua.dkovalov.fridge;

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
            for (byte lever : leversRow) {
                leversString += " " + (lever == LEVER_LOCKED ? '|' : '-');
            }
            leversString += "\n";
        }

        return leversString;
    }

    public boolean isUnlocked() {
        for (byte[] leversRow : levers) {
            for (byte lever : leversRow) {
                if (lever == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void rotateLever(LeverPosition leverPosition) {
        for (int j = 0; j < levers[leverPosition.getRow()].length; j++) {
            levers[leverPosition.getRow()][j] ^= LEVER_LOCKED;
        }
        for (int i = 0; i < levers.length; i++) {
            if (i != leverPosition.getRow()) {
                levers[i][leverPosition.getColumn()] ^= LEVER_LOCKED;
            }
        }
    }
}