package domowe;

public class CountingSemaphore {
    private int maxAccess;
    private BinarySemaphore binarySemaphore;

    public CountingSemaphore(int maxAccess, BinarySemaphore binarySemaphore){
        this.maxAccess = maxAccess;
        this.binarySemaphore = binarySemaphore;
    }

    public synchronized void countingSemWait() {
        if(maxAccess>0){
            if((--maxAccess) == 0){
                binarySemaphore.binarySemWait();
            }
        }
    }

    public synchronized void countingSemSignal() {
        if((maxAccess++) == 0){
            binarySemaphore.binarySemSignal();
        }
    }
}
