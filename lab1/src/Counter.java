public class Counter {
    private int counter;

    public Counter(int counter){
        this.counter = counter;
    }

    public void executeMethod(int method){
//        System.out.print(Thread.currentThread().getName() + " Before: ");
//        this.printValue();
        counter += method;
//        System.out.print(Thread.currentThread().getName() + " After: ");
//        this.printValue();
    }

    public void printValue(){
        System.out.println(counter);
    }
}
