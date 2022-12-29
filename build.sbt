ThisBuild / organization := "com.github.ymatigoosa"
ThisBuild / version      := "0.0.1"
ThisBuild / scalaVersion := "3.2.1"

lazy val symposiumApi = project
  .in(file("service/symposium/api"))
  .enablePlugins(ProjectKindServiceApi)

lazy val symposiumApp = project
  .in(file("service/symposium/app"))
  .enablePlugins(ProjectKindServiceApp)
  .dependsOn(symposiumApi)
