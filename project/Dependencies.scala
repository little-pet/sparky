import sbt._

object Dependencies {

  object Version {
    val spark = "2.3.2"
    val scalaTest = "3.0.5"
  }

  val sparkCore = "org.apache.spark" %% "spark-core" % Version.spark % Provided
  val sparkSql = "org.apache.spark" %% "spark-sql" % Version.spark % Provided
  val sparkHive = "org.apache.spark" %% "spark-hive" % Version.spark % Provided

  val sparkCoreTest = "org.apache.spark" %% "spark-core" % Version.spark % Test
  val sparkSqlTest = "org.apache.spark" %% "spark-sql" % Version.spark % Test
  val sparkHiveTest = "org.apache.spark" %% "spark-hive" % Version.spark % Test
  val sparkCatalystTest = "org.apache.spark" %% "spark-catalyst" % Version.spark % Test

  val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest % Test

  val sparky = Seq(
    sparkCore,
    sparkSql,
    sparkHive,
    sparkCoreTest,
    sparkCoreTest classifier "tests",
    sparkSqlTest,
    sparkSqlTest classifier "tests",
    sparkHiveTest,
    sparkCatalystTest,
    sparkCatalystTest classifier "tests",
    scalaTest
  )
}
