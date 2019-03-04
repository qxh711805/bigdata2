package com.rimi.kafka;

import org.junit.Test;

public class MyProducerTest {

    @Test
    public void sendMsg() {
        MyProducer myProducer = new MyProducer();
        myProducer.sendMsg("讲真的");
    }
}