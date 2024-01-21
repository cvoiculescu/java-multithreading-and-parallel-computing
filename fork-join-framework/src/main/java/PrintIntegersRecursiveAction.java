import java.util.concurrent.RecursiveAction;

public class PrintIntegersRecursiveAction extends RecursiveAction {

    int[] array;
    int lowIndex;
    int highIndex;

    public PrintIntegersRecursiveAction(int[] array) {
        this.array = array;
        this.lowIndex = 0;
        this.highIndex = array.length - 1;
    }

    public PrintIntegersRecursiveAction(int[] array, int lowIndex, int highIndex) {
        this.array = array;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected void compute() {
        if (highIndex - lowIndex < 2) {
            for (int i = lowIndex; i <= highIndex; i++) {
                System.out.println(array[i]);
            }
        } else {
            int mid = (lowIndex + highIndex) / 2;
            PrintIntegersRecursiveAction action1 = new PrintIntegersRecursiveAction(array, lowIndex, mid);
            PrintIntegersRecursiveAction action2 = new PrintIntegersRecursiveAction(array, mid+1, highIndex);
            invokeAll(action1, action2);
        }
    }
}
