package domowe;

public class Counter {
    private int counter;

    public Counter(int counter){
        this.counter = counter;
    }

    public int read(){
        return counter;
    }

    public void write(int value){
        System.out.print(Thread.currentThread().getName() + " Before: ");
        this.printValue();
        counter=value;
        System.out.print(Thread.currentThread().getName() + " After: ");
        this.printValue();
    }

    public void printValue(){
        System.out.println(counter);
    }
}
