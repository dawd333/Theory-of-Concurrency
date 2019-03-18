package laby;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class BufferPipeline {
    private final ArrayList<Integer> _buf;
    private Semaphore semaphore;

    public BufferPipeline(Semaphore semaphore) {
        _buf = new ArrayList<>(100);
        this.semaphore = semaphore;
    }

    public void put(int i){
        try {
            semaphore.acquire();
            System.out.println("Producer: " + Thread.currentThread().getName() + " " + i);
            _buf.add(i);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putAtIndex(int value, int index){
        try {
            semaphore.acquire();
            System.out.println("Process put at index " + index +  ": " + Thread.currentThread().getName() + " " + value);
            _buf.add(index, value);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getFromIndex(int index){
        int tmp = -1;
        try {
            semaphore.acquire();
            tmp = _buf.get(index);
            System.out.println("Process get at index " + index + ": " + Thread.currentThread().getName() + " " + tmp);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public int get() {
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
