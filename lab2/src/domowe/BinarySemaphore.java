package domowe;

class BinarySemaphore {
    private boolean semState;

    public BinarySemaphore() {
        semState = true;
    }

    public synchronized void binarySemWait() {
        while(!this.semState){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        if(!this.semState){
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        this.semState = false;
    }

    public synchronized void binarySemSignal() {
        this.semState = true;
        this.notify();
    }
}
