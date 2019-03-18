package domowe;

public class SynchronizedRace {
    public static void main(String[] args){
        BinarySemaphore binarySemaphore = new BinarySemaphore();
        Counter counter = new Counter(0);

        int amountOfThreads = 1000;
        SynchronizedCounterThread[] threads = new SynchronizedCounterThread[amountOfThreads];

        for (int i=0; i<amountOfThreads; i++){
            threads[i] = new SynchronizedCounterThread(counter, binarySemaphore);
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

        System.out.println("Ilość utworzonych watków: " + amountOfThreads);
        System.out.print("Licznik po zakończeniu: ");
        counter.printValue();
    }
}
