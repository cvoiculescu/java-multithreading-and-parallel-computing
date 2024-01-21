import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {

        if (simulatedWork > 100) {
            System.out.println(getName() + ": Parallel execution and split the task... " + simulatedWork);
            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork / 2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork / 2);
//            action1.fork();
//            action2.fork();
//            action1.join();
//            action2.join();
            invokeAll(action1, action2);
        } else {
            System.out.println(getName() + ": Simple task so sequential execution is fine..."+simulatedWork);
        }
    }

    private String getName() {
        return Thread.currentThread().getName();
    }

}
