class Paper{
    public synchronized void writeWithPaperAndPen(Pen pen){
        System.out.println(Thread.currentThread().getName() +" is using Paper "+this+" is trying to get pen.");
        pen.finishWriting();
    }
    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName()+" finished writing using paper "+this);
    }
}
class Pen{
    public synchronized void writeWithPenAndPaper(Paper paper){
        System.out.println(Thread.currentThread().getName() +" is using Pen "+this+" is trying to get paper.");
        paper.finishWriting();
    }
    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName()+" finished writing using pen "+this);
    }
}

class Task1 implements Runnable{
    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }
    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); //thread1 locks pen and tries to get paper
    }
}

class Task2 implements Runnable{
    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }
    @Override
    public void run() {
        //Changing consistent order of acquiring locks to resolve Deadlock...
        //First get the lock of Pen then acquire paper
        synchronized (pen){
            paper.writeWithPaperAndPen(pen);
        }
//        Below line alone causes deadlock
//        paper.writeWithPaperAndPen(pen); //thread2 locks paper and tries to get pen
    }
}

public class DeadLockEx {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1 = new Thread(new Task1(pen, paper), "PenWantsPaper");
        Thread t2 = new Thread(new Task2(pen, paper), "PaperWantsPen");

        t1.start();
        t2.start();

    }
}
