package domowe;

public class PhilosopherTest {

    //Kazdy z filozofow najpierw stara sie podniesc lewy widelec potem prawy, rozwiazanie to moze prowadzic do zakleszczenia
    private static void testPhilosopher0(){
        BinarySemaphore[] forks = new BinarySemaphore[5];
        for(int i=0; i<5; i++) forks[i] = new BinarySemaphore();

        Philosopher0[] philosophers = new Philosopher0[5];
        for(int i=0; i<5; i++) {
            philosophers[i] = new Philosopher0(i, forks);
        }

        for(int i=0; i<5; i++) {
            philosophers[i].start();
        }

        for(int i=0; i<5; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Rozwiazania problemu filozow z uzyciem kelnera, kelner obsluguje wydawanie i zabieranie widelcow pilnuje by nie doszlo do zakleszczenia
    private static void testPhilosopher1(){
        Waiter waiter = new Waiter();
        Philosopher1[] philosophers = new Philosopher1[5];
        for(int i=0; i<5; i++) {
            philosophers[i] = new Philosopher1(i, waiter);
        }

        for(int i=0; i<5; i++) {
            philosophers[i].start();
        }

        for(int i=0; i<5; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Rozwiazanie problemu filozow z uzyciem hierarchi zasobow, czesciowe uporzadkowanie widelcow, najpierw trzeba wziac widelec o mniejszym priorytecie, nastepnie ten o wiekszym, oddac odwrotnie, brak zakleszczen
    private static void testPhilosopher2(){
        BinarySemaphore[] forks = new BinarySemaphore[5];
        for(int i=0; i<5; i++) forks[i] = new BinarySemaphore();

        Philosopher2[] philosophers = new Philosopher2[5];
        for(int i=0; i<5; i++) {
            philosophers[i] = new Philosopher2(i, forks);
        }

        for(int i=0; i<5; i++) {
            philosophers[i].start();
        }

        for(int i=0; i<5; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Rozwiazanie problemu filozow Chandy/Mistra
    private static void testPhilosopher3(){
        boolean[] forks = new boolean[5];
        for(int i=0; i<5; i++) forks[i] = false;

        int[] membership = new int[5];
        for(int i=0; i<5; i++){
            if(i==0){
                membership[i] = i;
            } else {
                membership[i] = i-1;
            }
        }

        boolean[] isEating = new boolean[5];
        for(int i=0; i<5; i++) isEating[i] = false;

        Philosopher3[] philosophers = new Philosopher3[5];
        for(int i=0; i<5; i++) {
            philosophers[i] = new Philosopher3(i, forks, membership, philosophers, isEating);
        }

        for(int i=0; i<5; i++) {
            philosophers[i].start();
        }

        for(int i=0; i<5; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
//        testPhilosopher0();
        testPhilosopher1();
//        testPhilosopher2();
//        testPhilosopher3();
    }
}
