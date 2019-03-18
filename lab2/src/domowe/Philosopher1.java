package domowe;

import java.util.Random;

public class Philosopher1 extends Thread {
    private int n; //"number" of the philosopher
    private Waiter waiter;

    public Philosopher1(int n, Waiter waiter){
        this.n = n;
        this.waiter = waiter;
    }

    public void run(){
        Random rand = new Random();
        for (int i=0; i<5; i++){
            waiter.distributeForks(n);

            System.out.println(n + " started eating");

            try {
                Thread.sleep(rand.nextInt(500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(n + " ended eating");
            waiter.collectForks(n);

            System.out.println(n + " is thinking...");

            try {
                Thread.sleep(rand.nextInt(500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
