public class CounterThread extends Thread {
    private Counter counter;
    private int method;

    public CounterThread(Counter counter, int method){
        this.counter = counter;
        this.method = method;
    }

    public void run(){
        for (int i=0; i<10000000; i++){
            counter.executeMethod(method);
        }
    }
}