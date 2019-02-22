package com.sparky.sql

import org.apache.spark.sql.types.{BooleanType, DataType, DecimalType, StringType}

object types {

  trait TypeMapping[T] {
    def dataType: DataType
  }

  implicit object BigDecimalMapping extends TypeMapping[BigDecimal] {
    override def dataType: DataType = DecimalType.SYSTEM_DEFAULT
  }

  implicit object BooleanMapping extends TypeMapping[Boolean] {
    override def dataType: DataType = BooleanType
  }

  implicit object StringMapping extends TypeMapping[String] {
    override def dataType: DataType = StringType
  }

}
