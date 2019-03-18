public class SynchronizedCounterThread extends Thread {
    private SynchronizedCounter counter;
    private int method;

    public SynchronizedCounterThread(SynchronizedCounter counter, int method){
        this.counter = counter;
        this.method = method;
    }

    public void run(){
        for (int i=0; i<10000000; i++){
            counter.executeMethod(method);
        }
    }
}