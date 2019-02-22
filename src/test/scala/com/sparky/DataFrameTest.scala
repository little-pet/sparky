package com.sparky

import com.sparky.sql.functions._
import org.apache.spark.sql.functions.col

class DataFrameTest extends BaseSparkSuite {
  test(testName = "null_to_zero") {

    val df = spark
      .createDataFrame(
        Seq(
          (0, None),
          (0, Some(0))
        ))
      .toDF("id1", "id2")

    df.where(null_to_zero(col(colName = "id2")) === col(colName = "id1")).collect().length shouldBe 2

  }
}
