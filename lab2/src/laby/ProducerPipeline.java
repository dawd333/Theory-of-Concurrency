package laby;

public class ProducerPipeline extends Thread {
    private final BufferPipeline _buf;

    public ProducerPipeline(BufferPipeline _buf){
        this._buf = _buf;
    }

    public void run() {
        for(int i=0; i<100; i++){
            _buf.put(i);
        }
    }
}
