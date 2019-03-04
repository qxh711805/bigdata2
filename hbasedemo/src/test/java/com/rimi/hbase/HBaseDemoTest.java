package com.rimi.hbase;

import org.junit.Test;

import static org.junit.Assert.*;

public class HBaseDemoTest {

    @Test
    public void put() throws Exception {
        HBaseDemo hBaseDemo = new HBaseDemo();
        hBaseDemo.put();
    }

    @Test
    public void get() throws Exception {
        HBaseDemo hBaseDemo = new HBaseDemo();
        hBaseDemo.get();
    }

    @Test
    public void put2() throws Exception {
        HBaseDemo hBaseDemo = new HBaseDemo();
        hBaseDemo.put2();
    }
    @Test
    public void dropTable() throws Exception {
        HBaseDemo hBaseDemo = new HBaseDemo();
        hBaseDemo.dropTable();
    }

    @Test
    public void createTable() throws Exception {
        HBaseDemo hBaseDemo = new HBaseDemo();
        hBaseDemo.createTable();
    }

    @Test
    public void scan() throws Exception {
        HBaseDemo hBaseDemo = new HBaseDemo();
        hBaseDemo.scan();
    }

    @Test
    public void split() throws Exception {
        HBaseDemo hBaseDemo = new HBaseDemo();
        hBaseDemo.split();
    }

}