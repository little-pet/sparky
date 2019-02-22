package com.sparky

import org.apache.spark.sql.types.{StringType, StructField, StructType}


class SchemaTest extends BaseSparkSuite {

  test(testName = "customEquals") {
    val s1 = StructType(
      Seq(
        StructField("name", StringType, nullable = true),
        StructField("address", StringType, nullable = true)
      )
    )

    val s2 = StructType(
      Seq(
        StructField("name", StringType, nullable = false),
        StructField("address", StringType, nullable = false)
      )
    )

    schema.customEquals(s1, s2)(ignoreNullable = true)(ignoreColumnNames = false) shouldBe true

    schema.customEquals(s1, s2)(ignoreNullable = false)(ignoreColumnNames = false) shouldBe false
  }

}
