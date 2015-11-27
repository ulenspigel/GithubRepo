/**
 * Created by dgkovalev on 11/27/2015.
 */
public class LeverIndex {
    public int row;
    public int column;

    public LeverIndex(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "[" + row + ";" + column + "]";
    }
}
