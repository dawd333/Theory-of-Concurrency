package laby;

public class ProcessPipeline extends Thread {
    private final BufferPipeline _buf;

    public ProcessPipeline(BufferPipeline _buf) {
        this._buf = _buf;
    }

    public void run() {
        for (int i=0; i<100; i++){
            int tmp = _buf.getFromIndex(i);
            tmp += 1;
            _buf.putAtIndex(tmp, i);
        }
    }
}
