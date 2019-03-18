package domowe;

public class SynchronizedCounterThread extends Thread {
    private Counter counter;
    private BinarySemaphore binarySemaphore;

    public SynchronizedCounterThread(Counter counter, BinarySemaphore binarySemaphore){
        this.counter = counter;
        this.binarySemaphore = binarySemaphore;
    }

    public void run(){
        for (int i=0; i<5; i++){
            binarySemaphore.binarySemWait();
            int tmp = counter.read();
            tmp++;
            counter.write(tmp);
            binarySemaphore.binarySemSignal();
        }
    }
}
