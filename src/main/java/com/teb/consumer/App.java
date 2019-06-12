package com.teb.consumer;

import com.teb.consumer.Consumer.ConsumerThread;
import com.teb.consumer.Factory.DataViewerFactory;

public class App {

    public static void main(String[] args) {
        try {
//            ConsumerThread consumerRunnable = new ConsumerThread();
//            consumerRunnable.run();
            runChart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runChart() throws InterruptedException {

        DataViewerFactory.createDataViewerExample1(); // http://localhost:8090/view/example1

//		 http://localhost:8090/view/updateExample
//        DataViewerFactory.createDataViewerUpdateExample();

        Thread.currentThread().join();
    }

}
