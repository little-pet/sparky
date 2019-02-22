package com.sparky

import org.apache.spark.sql.types.{StructField, StructType}

package object schema {
  def customEquals(s1: StructType, s2: StructType)
                  (ignoreNullable: Boolean)
                  (ignoreColumnNames: Boolean): Boolean = {
    if (s1.length != s2.length) {
      false
    } else {
      val structFields: Seq[(StructField, StructField)] = s1.zip(s2)

      structFields.forall { case (f1: StructField, f2: StructField) =>
        ((f1.nullable == f2.nullable) || ignoreNullable) &&
          ((f1.name == f2.name) || ignoreColumnNames) &&
          (f1.dataType == f2.dataType)
      }
    }
  }
}
