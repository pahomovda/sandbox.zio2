ThisBuild / organization := "com.github.ymatigoosa"
ThisBuild / version := "0.0.1"
ThisBuild / scalaVersion := "3.2.1"

lazy val symposiumApi = project
  .in(file("service/symposium/api"))
  .settings(
    libraryDependencies ++= Dependency.tapirCore,
    libraryDependencies ++= Dependency.tapirJsonZio,
  )

lazy val symposiumApp = project
  .in(file("service/symposium/app"))
  .dependsOn(symposiumApi)
  .settings(Settings.zioTest)
  .settings(
    libraryDependencies ++= Dependency.zio,
    libraryDependencies ++= Dependency.tapirJsonZio,
    libraryDependencies ++= Dependency.tapirZio,
    libraryDependencies ++= Dependency.tapirZioHttpServer,
  )
