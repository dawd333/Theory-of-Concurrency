package domowe;

public class Race {
    public static void main(String[] args){
        Counter counter = new Counter(0);

        int amountOfThreads = 1000;
        CounterThread[] threads = new CounterThread[amountOfThreads];

        for (int i=0; i<amountOfThreads; i++){
            threads[i] = new CounterThread(counter);
        }

        for (int i=0; i<amountOfThreads; i++){
            threads[i].start();
        }

        for (int i=0; i<amountOfThreads; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter.printValue();
    }
}
