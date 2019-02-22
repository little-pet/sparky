package com.sparky.sql

import java.sql.Timestamp

import com.sparky.sql.types.TypeMapping
import org.apache.spark.sql.Column
import org.apache.spark.sql.catalyst.expressions.NamedExpression
import org.apache.spark.sql.functions._
import com.sparky.extensions.TimestampExtensions.TimestampModule

/**
  * Additional functions for DataFrame operations.
  */
object functions {
  /**
    * Apply expression to column preserving column's name if any.
    */
  private def map_column(col: Column, f: Column => Column): Column = {
    col.expr match {
      case named: NamedExpression => f(col).as(named.name)
      case _ => f(col)
    }
  }

  /**
    * Replace null value with zero.
    */
  def null_to_zero(col: Column): Column = {
    map_column(col, coalesce(_, lit(literal = 0)))
  }

  /**
    * Replace null value with false.
    */
  def null_to_false(col: Column): Column = {
    map_column(col, coalesce(_, lit(literal = false)))
  }

  /**
    * Convert decimal type to plain string stripping trailing zeros.
    */
  def to_plain_string_strip_trailing_zeros(col: Column): Column = {
    val f = udf { x: java.math.BigDecimal => Option(x).map(_.stripTrailingZeros.toPlainString) }
    map_column(col, f(_))
  }

  /**
    * Null column with type information.
    */
  def typed_null[T: TypeMapping]: Column = {
    lit(null).cast(implicitly[TypeMapping[T]].dataType)
  }

  /**
    * Trim column then replace whitespace with null.
    */
  def trim_to_null(col: Column): Column = {
    map_column(col, { c =>
      val trimmed = trim(col)
      when(trimmed === lit(literal = ""), lit(literal = null)).otherwise(trimmed)
    })
  }

  /**
    * Add number of years.
    */
  def add_years(col: Column, numYears: Int): Column = {
    val addYearsUdf = udf { dateOrNull: Timestamp => Option(dateOrNull).map(_.plusYears(numYears)) }
    map_column(col, addYearsUdf(_))
  }
}
