name := "sparky"

val releaseVersion = "0.0.1"

lazy val buildSettings = Seq(
  organization := "com.sparky",
  version := releaseVersion,
  scalaVersion := "2.12.8"
)

lazy val compilerSettings = scala.Seq[Def.Setting[_]](
  scalacOptions += "-unchecked",
  scalacOptions += "-deprecation"
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