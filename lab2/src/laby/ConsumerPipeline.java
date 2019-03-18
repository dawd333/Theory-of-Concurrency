package laby;

public class ConsumerPipeline extends Thread {

    private final BufferPipeline _buf;

    public ConsumerPipeline(BufferPipeline _buf){
        this._buf = _buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {

            int tmp = _buf.get();
            if(tmp != -1){
                System.out.println("Consumer: " + this.getName() + " " + tmp);
            }
            else{
                i--;
            }
        }
    }
}
