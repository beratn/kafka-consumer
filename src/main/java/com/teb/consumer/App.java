package com.teb.consumer;

import com.teb.consumer.Consumer.ConsumerThread;

public class App {

    public static void main(String[] args) {
        ConsumerThread consumerRunnable = new ConsumerThread();
        consumerRunnable.run();

    }



}
