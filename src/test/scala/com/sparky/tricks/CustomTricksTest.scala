package com.sparky.tricks

import com.sparky.BaseSparkSuite

class CustomTricksTest extends BaseSparkSuite {

  test(testName = "Shuffle free joins in spark sql") {
    val sparkSession = spark
    import sparkSession.implicits._

    val data1s = Range(0, 1000).map(x => Data1(s"key${x}", x))
    val data2s = Range(0, 1000).map(x => Data2(s"key${x}", x))

    val df1 = spark.sparkContext.parallelize(data1s).toDF
    val df2 = spark.sparkContext.parallelize(data2s).toDF
//    df1.count
//    df2.count
//    val joined = df1.join(df2, df1("key") === df2("key2"))
//    joined.count
//    joined.explain


    val partitioned1 = df1.repartition(df1("key")).sortWithinPartitions(df1("key")).cache()
    val partitioned2 = df2.repartition(df2("key2")).sortWithinPartitions(df2("key2")).cache()
    partitioned1.count
    partitioned2.count

    val ps_joined = partitioned1.join(partitioned2, partitioned1("key") === partitioned2("key2"))
    ps_joined.count
    ps_joined.explain

  }

}
