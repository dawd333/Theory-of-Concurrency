public class SynchronizedCounter {
    private int counter;

    public SynchronizedCounter(int counter){
        this.counter = counter;
    }

    public void executeMethod(int method){
        synchronized (this){
//            System.out.print(Thread.currentThread().getName() + " Before2: ");
//            this.printValue();
            counter += method;
//            System.out.print(Thread.currentThread().getName() + " After2: ");
//            this.printValue();
        }
    }

    public void printValue(){
        System.out.println(counter);
    }
}
