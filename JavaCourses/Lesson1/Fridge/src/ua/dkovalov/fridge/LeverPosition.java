package ua.dkovalov.fridge;

public class LeverPosition {
    public int row;
    public int column;

    public LeverPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "[" + row + ";" + column + "]";
    }

    @Override
    public boolean equals(Object leverPosition) {
        return (row == ((LeverPosition) leverPosition).getRow() && column == ((LeverPosition) leverPosition).getColumn());
    }
}
