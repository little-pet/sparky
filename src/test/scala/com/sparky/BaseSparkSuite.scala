package com.sparky

import org.apache.spark.{SharedSparkContext, SparkFunSuite}
import org.scalatest.Matchers

trait BaseSparkSuite extends SparkFunSuite with SharedSparkContext with Matchers {

  override def beforeAll(): Unit = {
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    super.afterAll()
  }

}
