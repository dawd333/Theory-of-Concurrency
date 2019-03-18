package domowe;

import java.util.Random;

public class Philosopher3 extends Thread {
    private int n; //"number" of the philosopher
    private boolean[] forks; //true - czyste widelce, false - brudne
    private int[] owners;
    private Philosopher3[] philosophers;
    private boolean[] isEating;

    public Philosopher3(int n, boolean[] forks, int[] owners, Philosopher3[] philosophers, boolean[] isEating){
        this.n = n;
        this.forks = forks;
        this.owners = owners;
        this.philosophers = philosophers;
        this.isEating = isEating;
    }

    public synchronized void getFork(int i, int n){
        if(!forks[i] && !isEating[owners[i]]){
            forks[i] = true;
            owners[i] = n;
        }
    }

    public synchronized void takeForks(int i) {
        Random rand = new Random();
        while(owners[i] != n){
            while(isEating[owners[i]]){
                try {
                    Thread.sleep(rand.nextInt(500) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            philosophers[owners[i]].getFork(i, n);
            if(owners[i] == n) break;
        }

        while(owners[(i+1)%5] != n){
            while(isEating[owners[(i+1)%5]]){
                try {
                    Thread.sleep(rand.nextInt(500) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            philosophers[owners[(i+1)%5]].getFork((i+1)%5, n);
            if(owners[(i+1)%5] == n) break;
        }
        isEating[n] = true;
    }

    public synchronized void putForks(int i) {
        forks[i] = false;
        forks[(i+1)%5] = false;
        isEating[n] = false;

    }

    public void run(){
        Random rand = new Random();
        for (int i=0; i<5; i++){
            takeForks(n);

            System.out.println(n + " started eating");

            try {
                Thread.sleep(rand.nextInt(500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
