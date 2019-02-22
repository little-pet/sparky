import sbt.Keys.scalacOptions

name := "sparky"

val releaseVersion = "0.0.1"

lazy val buildSettings = Seq(
  organization := "com.sparky",
  version := releaseVersion,
  scalaVersion := "2.11.12"
)

lazy val compilerSettings = scala.Seq[Def.Setting[_]](
  scalacOptions += "-deprecation",
  scalacOptions += "-encoding",
  scalacOptions += "utf-8",
  scalacOptions += "-explaintypes",
  scalacOptions += "-language:higherKinds",
  scalacOptions += "-unchecked"
)

lazy val testSettings = scala.Seq[Def.Setting[_]](
  fork in Test := true,
  parallelExecution in Test := false,
  javaOptions ++= Seq("-Xms512M", "-Xmx2048M")
)

lazy val staticAnalyzerSettings = scala.Seq[Def.Setting[_]](
  scalacOptions += "-P:wartremover:only-warn-traverser:org.wartremover.warts.Unsafe"
)

lazy val repositoriesSettings = Seq(
  resolvers += "Mvnrepository" at "https://mvnrepository.com/artifact/"
)

lazy val sparky = Project(
  id = "sparky",
  base = file(".")
).settings(buildSettings)
  .settings(compilerSettings)
  .settings(repositoriesSettings)
  .settings(staticAnalyzerSettings)
  .settings(testSettings)
  .settings(
    libraryDependencies ++= Dependencies.sparky
  )