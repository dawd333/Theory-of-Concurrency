package laby;

import java.util.concurrent.Semaphore;

public class PKmon {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        Buffer _buf = new Buffer(semaphore);

        //Problem ograniczonego bufora dla 1prod/1kons z wyk. monitora

//        laby.Producer producer = new laby.Producer(_buf);
//        laby.Consumer consumer = new laby.Consumer(_buf);
//        producer.start();
//        consumer.start();
//        try {
//            producer.join();
//            consumer.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //Problem ograniczonego bufora dla n prod/n kons z wyk. monitora

//        laby.Producer[] producers = new laby.Producer[20];
//        laby.Consumer[] consumers = new laby.Consumer[20];
//        for(int i=0; i<20; i++){
//            producers[i] = new laby.Producer(_buf);
//        }
//        for(int i=0; i<20; i++){
//            consumers[i] = new laby.Consumer(_buf);
//        }
//        for(int i=0; i<20; i++){
//            producers[i].start();
//            consumers[i].start();
//        }
//        for(int i=0; i<20; i++){
//            try {
//                producers[i].join();
//                consumers[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


        //Problem ograniczonego bufora dla 1prod/1kons z wyk. semaphorow

//        ProducerSemaphore producer = new ProducerSemaphore(_buf);
//        ConsumerSemaphore consumer = new ConsumerSemaphore(_buf);
//        producer.start();
//        consumer.start();
//        try {
//            producer.join();
//            consumer.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //Problem ograniczonego bufora dla n prod/n kons z wyk. semaphorow

//        ProducerSemaphore[] producers = new ProducerSemaphore[20];
//        ConsumerSemaphore[] consumers = new ConsumerSemaphore[20];
//        for(int i=0; i<20; i++){
//            producers[i] = new ProducerSemaphore(_buf);
//        }
//        for(int i=0; i<20; i++){
//            consumers[i] = new ConsumerSemaphore(_buf);
//        }
//        for(int i=0; i<20; i++){
//            producers[i].start();
//            consumers[i].start();
//        }
//        for(int i=0; i<20; i++){
//            try {
//                producers[i].join();
//                consumers[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


        //Problem przetwarzania potokowego z buforem

//        Semaphore sem = new Semaphore(1);
//        BufferPipeline buf = new BufferPipeline(sem);
//
//        ProducerPipeline producer = new ProducerPipeline(buf);
//        producer.start();
//        try {
//            producer.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ProcessPipeline[] processes = new ProcessPipeline[5];
//        for(int i=0; i<5; i++){
//            processes[i] = new ProcessPipeline(buf);
//            processes[i].start();
//            try {
//                processes[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        ConsumerPipeline consumer = new ConsumerPipeline(buf);
//        consumer.start();
//        try {
//            consumer.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}