package com.rimi.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * 过滤器
 */
public class HBaseFilter {

    public void rowFilter() throws Exception {
        Configuration entries = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(entries);
        Table table = connection.getTable(TableName.valueOf("ns1:t2"));
        // 创建扫描器
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("name"));
        // 创建行过滤器
        Filter filter = new RowFilter(CompareOperator.LESS_OR_EQUAL,new BinaryComparator(Bytes.toBytes("row000100")));
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            System.out.println(result);
        }
        scanner.close();

        // 列族过滤器
//        FamilyFilter()

    }
}
