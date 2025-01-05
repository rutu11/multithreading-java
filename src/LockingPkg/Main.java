package LockingPkg;

public class Main {
    public static void main(String[] args) {
        BankAccount sbi = new BankAccount();
        Runnable runTask = new Runnable() {
            @Override
            public void run() {
                sbi.withdraw(50);
            }
        };
        Thread t1 = new Thread(runTask,"Rutuja");
        Thread t2 = new Thread(runTask, "Yash");
        Thread t3 = new Thread(runTask, "Geetika");

        t1.start();
        t2.start();
        t2.interrupt();
        t3.start();
    }
}
