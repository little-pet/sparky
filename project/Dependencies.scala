import sbt._

object Dependencies {

  object Version {
    val spark = "2.4.0"
    val scalaTest = "3.2.0-SNAP10"
    val wartremover = "2.4.1"
  }

  val sparkCore = "org.apache.spark" %% "spark-core" % Version.spark % Provided
  val sparkSql = "org.apache.spark" %% "spark-sql" % Version.spark % Provided
  val sparkHive = "org.apache.spark" %% "spark-hive" % Version.spark % Provided

  val sparkCoreTest = "org.apache.spark" %% "spark-core" % Version.spark % Test
  val sparkSqlTest = "org.apache.spark" %% "spark-sql" % Version.spark % Test
  val sparkHiveTest = "org.apache.spark" %% "spark-hive" % Version.spark % Test

  val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest % Test
  val wartremoverPlugin = compilerPlugin(dependency = "org.wartremover" %% "wartremover" % Version.wartremover)

  val sparky = Seq(
    sparkCore,
    sparkSql,
    sparkHive,
    sparkCoreTest,
    sparkCoreTest classifier "tests",
    sparkSqlTest,
    sparkSqlTest classifier "tests",
    sparkHiveTest,
    scalaTest,
    wartremoverPlugin
  )

}
