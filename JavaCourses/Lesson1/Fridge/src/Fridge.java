/**
 * Created by dgkovalev on 11/27/2015.
 */
public class Fridge {
    public static void main(String... args) {
        byte[][] initialLevers = {
                {1, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 0, 1, 1},
                {0, 1, 0, 1}
        };

        FridgeOpener fridgeOpener = new FridgeOpener(new FridgeLock(initialLevers));
        //FridgeOpener fridgeOpener = new FridgeOpener();
        //fridgeOpener.generateRandomLock();
        System.out.println("Fridge lock init state: \n" + fridgeOpener.getFridgeLock());
        try {
            fridgeOpener.open();
            System.out.println("Fridge lock opened: \n" + fridgeOpener.getFridgeLock());
            System.out.println("Number of iterations: " + fridgeOpener.getIterations());
            System.out.println("Rotated levers: \n" + fridgeOpener.getRotatedLevers());
        } catch (Exception e) {
            System.out.println("Failed to open the fridge: \n" + e.getMessage());
        }
    }
}
