package com.rimi.spark.java;

import org.apache.spark.sql.*;

public class JavaSparkSQLDemo {
    public static void main(String[] args) throws AnalysisException {

        //  获得sparkSession对象
        SparkSession spark = SparkSession.builder().appName("SparkSQL").master("local[4]").getOrCreate();

        // 读取文件
        Dataset<Row> users = spark.read().json("C:\\Users\\admin\\Desktop\\sql\\Users.json");

        // 输出信息
//        users.printSchema();

        // 查看数据
//        users.show();

        // 使用 DataFrame API中的方法
//        users.select("id","name","age").show();
//        users.select(new Column("id")).show();

        // 使用 spark SQL
        // 1.创建临时视图(临时表)
//        users.createTempView("user");
        // 2.调用 SparksSession中的sql方法
//        spark.sql("select id,name,age from user").show();


        Dataset<Row> dataset = users.filter("age > 20");
//        dataset.write().save("C:\\Users\\admin\\Desktop\\sql\\out");
//        users.write().json("C:\\Users\\admin\\Desktop\\sql\\out1");
        users.write().csv("C:\\Users\\admin\\Desktop\\sql\\out2");
    }
}
