package domowe;

import java.util.Random;

public class Philosopher0 extends Thread {
    private int n; //"number" of the philosopher
    private BinarySemaphore[] forks;

    public Philosopher0(int n, BinarySemaphore[] forks) {
        this.n = n;
        this.forks = forks;
    }

    public synchronized void takeForks(int i) {
        forks[i].binarySemWait();
        forks[(i + 1) % 5].binarySemWait();
    }

    public synchronized void putForks(int i) {
        forks[i].binarySemSignal();
        forks[(i + 1) % 5].binarySemSignal();
    }

    public void run() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            takeForks(n);

            System.out.println(n + " started eating");

            try {
                Thread.sleep(rand.nextInt(500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(n + " ended eating");
            putForks(n);

            System.out.println(n + " is thinking...");

            try {
                Thread.sleep(rand.nextInt(500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
