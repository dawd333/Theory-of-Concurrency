package laby;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Buffer {
    private final ArrayList<Integer> _buf;
    private Semaphore semaphore;

    public Buffer(Semaphore semaphore) {
        _buf = new ArrayList<>();
        this.semaphore = semaphore;
    }

    //put z monitorem
    public void put(int i) {
        synchronized (_buf) {
            System.out.println("laby.Producer: " + Thread.currentThread().getName() + " " + i);
            _buf.add(i);
            _buf.notifyAll();
        }
    }

    //put z semaphorem
    public void putWithSemaphore(int i){
        try {
            semaphore.acquire();
            System.out.println("laby.Producer sem: " + Thread.currentThread().getName() + " " + i);
            _buf.add(i);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get z monitorem
    public int get() {
        synchronized (_buf) {
            while (_buf.isEmpty()) {
                try {
                    _buf.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return _buf.remove(0);
        }
    }

    //get z semaphorem
    public int getWithSemaphore() {
        int tmp = -1;
        try {
            semaphore.acquire();
            if(!_buf.isEmpty()) {
                tmp = _buf.remove(0);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
        return tmp;
    }
}