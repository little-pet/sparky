package com.sparky

import org.apache.spark.SparkFunSuite
import org.apache.spark.sql.test.SharedSparkSession
import org.scalatest.Matchers

trait BaseSparkSuite extends SparkFunSuite with SharedSparkSession with Matchers {

  override def beforeAll(): Unit = {
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    super.afterAll()
  }

}
