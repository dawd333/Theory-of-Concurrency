package domowe;

public class CounterThread extends Thread {
    private Counter counter;

    public CounterThread(Counter counter){
        this.counter = counter;
    }

    public void run(){
        for (int i=0; i<5; i++){
            int tmp = counter.read();
            tmp++;
            counter.write(tmp);
        }
    }
}
