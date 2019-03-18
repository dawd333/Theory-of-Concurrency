public class Main {

    //Necessary for testing how much thread can be created
    private static Object s = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        Counter counter = new Counter(10);
        CounterThread add = new CounterThread(counter, 1);
        CounterThread sub = new CounterThread(counter, -1);

        SynchronizedCounter counter2 = new SynchronizedCounter(10);
        SynchronizedCounterThread add2 = new SynchronizedCounterThread(counter2, 1);
        SynchronizedCounterThread sub2 = new SynchronizedCounterThread(counter2, -1);

        add.start();
        sub.start();
        add2.start();
        sub2.start();
        try {
            add.join();
            sub.join();
            add2.join();
            sub2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        counter.printValue();
        counter2.printValue();

//        for(;;) {
//            new Thread(new Runnable() {
//                public void run() {
//                    synchronized (s) {
//                        count += 1;
//                        System.err.println("New thread #" + count);
//                    }
//                    for (;;) {
//                        try {
//                            Thread.sleep(1000);
//                        } catch (Exception e) {
//                            System.err.println(e);
//                        }
//                    }
//                }
//            }).start();
//        }
    }
}
