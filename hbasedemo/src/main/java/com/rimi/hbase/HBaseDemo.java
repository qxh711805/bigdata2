package com.rimi.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Random;

/**
 * HBase CRUD
 */
public class HBaseDemo {

    private static Configuration entries;
    private static Connection connection;

    static {
        try {
            entries = HBaseConfiguration.create();
            connection = ConnectionFactory.createConnection(entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增数据
     */
    public void put() throws Exception {
        // 创建所需的配置文件
        Configuration configuration = HBaseConfiguration.create();
        // 创建连接
        Connection connection = ConnectionFactory.createConnection(configuration);
        // 创建表名
        TableName tableName = TableName.valueOf("ns1:t1");
        // 获得表实例
        Table table = connection.getTable(tableName);


        Put put = new Put(Bytes.toBytes("row1"));
        // 添加列数据
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("id"), Bytes.toBytes(1));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("tom"));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"), Bytes.toBytes(16));
        // 添加值
        table.put(put);

    }

    public void get() throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        Table table = connection.getTable(TableName.valueOf("ns1:t1"));
        Get get = new Get(Bytes.toBytes("row1"));
        Result result = table.get(get);
        byte[] id = result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("id"));
        byte[] name = result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("name"));
        byte[] age = result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("age"));
        System.out.println("id:" + Bytes.toInt(id));
        System.out.println("name:" + Bytes.toString(name));
        System.out.println("age:" + Bytes.toInt(age));
    }

    public void put2() throws Exception {
        //插入 100000 数据
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);

        DecimalFormat format = new DecimalFormat();
        format.applyPattern("000000");


        HTable table = (HTable) connection.getTable(TableName.valueOf("ns1:t2"));
        for (int i = 1; i <= 100000; i++) {
            Put put = new Put(Bytes.toBytes("row" + format.format(i)));
            // 跳过预写日志
            put.setDurability(Durability.SKIP_WAL);
            // 添加列数据
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("id"), Bytes.toBytes(i));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("tom" + format.format(i)));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"), Bytes.toBytes(10 + new Random().nextInt(90)));

            table.put(put);
        }

    }

    /**
     * 删除表
     */
    public void dropTable() throws Exception {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();
        //首先要把表给禁用后,才能删除
        TableName tableName = TableName.valueOf("ns1:t2");
        // 禁用表
//        admin.disableTable(tableName);
        // 删除表
        admin.deleteTable(tableName);
        //关闭连接
        connection.close();
    }

    public void createTable() throws Exception {
        Configuration entries = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(entries);
        Admin admin = connection.getAdmin();

        // create 'ns1:t1','f1'
        TableName tableName = TableName.valueOf("ns1:t2");
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);

        // 创建列族
        ColumnFamilyDescriptor familyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("f1")).build();
        tableDescriptorBuilder.setColumnFamily(familyDescriptor);
        TableDescriptor descriptor = tableDescriptorBuilder.build();
        admin.createTable(descriptor);
        /*
        admin.createTable(TableDescriptorBuilder.newBuilder(TableName.valueOf("ns1:t1")).setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("f1")).build()).build());
        */
        connection.close();

    }

    public void scan()throws Exception {
        Table table = connection.getTable(TableName.valueOf("ns1:t2"));

        Scan scan = new Scan();
        // 设置扫描器是开始位置
        scan.withStartRow(Bytes.toBytes("row000001"));
        // 设置扫描器结束位置
        scan.withStopRow(Bytes.toBytes("row000100"));
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()){
            Result next = iterator.next();
            byte[] id = next.getValue(Bytes.toBytes("f1"), Bytes.toBytes("id"));
            byte[] name = next.getValue(Bytes.toBytes("f1"), Bytes.toBytes("name"));
            byte[] age = next.getValue(Bytes.toBytes("f1"), Bytes.toBytes("age"));
            System.out.println("id:"+Bytes.toInt(id)+"-"+"name:"+Bytes.toString(name)+"-" +"age:" + Bytes.toInt(age));
        }
    }

    /**
     * 切割表
     * @throws Exception
     */
    public void split() throws Exception {
        Admin admin = connection.getAdmin();
        admin.split(TableName.valueOf("ns1:t2"),Bytes.toBytes("row006000"));
    }

    /**
     * 移动区域
     * @throws Exception
     */
    public void move() throws Exception {
        Admin admin = connection.getAdmin();
        admin.move(Bytes.toBytes("158969856542586dae217dc249d785f4"),Bytes.toBytes("hd1,16020,1534239445232"));
    }

    /**
     * 合并区域
     * @throws Exception
     */
    public void merge() throws Exception {
        Admin admin = connection.getAdmin();
        admin.mergeRegionsAsync(Bytes.toBytes("158969856542586dae217dc249d785f4"),Bytes.toBytes("411cfdef93aa4714f4f78e941f178711"),true);
    }

}
