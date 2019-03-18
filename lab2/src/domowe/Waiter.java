package domowe;

public class Waiter {
    private boolean[] forks = new boolean[5];

    public Waiter(){
        for(int i=0; i<5; i++) forks[i] = true;
    }

    public synchronized void collectForks(int i){
        forks[i] = forks[(i+1)%5] = true;
        this.notify();
    }

    public synchronized void distributeForks(int i){
        while(!forks[i] || !forks[(i+1)%5]) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        forks[i] = forks[(i+1)%5] = false;
    }
}
