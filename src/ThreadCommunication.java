class SharedResource{
    private int data;
    private boolean hasData;

    public synchronized void produce(int value){
        while(hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); //restored exception
            }
        }
        data = value;
        System.out.println(Thread.currentThread().getName()+" Produced: "+data);
        hasData = true;
        notify();
    }
    public synchronized int consume(){
        while(!hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        notify();
        System.out.println(Thread.currentThread().getName()+" Consumed: "+data);
        return data;

    }
}

class Produce implements Runnable{
    private SharedResource resource;

    public Produce(SharedResource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            resource.produce(i);
        }
    }
}

class Consumer implements Runnable{
    private SharedResource resource;

    public Consumer(SharedResource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            int value = resource.consume();
        }
    }
}
public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread producerThread = new Thread(new Produce(resource), "Producer Thread");
        Thread consumerThread = new Thread(new Consumer(resource),"Consumer Thread");

        producerThread.start();
        consumerThread.start();

    }
}
