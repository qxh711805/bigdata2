package com.rimi.kafka;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyConsumerTest {

    @Test
    public void poll(){
        MyConsumer myConsumer = new MyConsumer();
        myConsumer.poll();
    }

}