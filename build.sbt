val zioVersion = "2.0.5"

lazy val root = project
  .in(file("."))
  .settings(
    inThisBuild(
      List(
        name := "sandbox.zio2",
        organization := "com.github.ymatigoosa",
        version := "0.0.1",
        scalaVersion := "3.2.2-RC2"
      )
    ),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"               % zioVersion,
      "dev.zio" %% "zio-test"          % zioVersion % Test,
      "dev.zio" %% "zio-test-sbt"      % zioVersion % Test,
      "dev.zio" %% "zio-test-junit"    % zioVersion % Test,
      "dev.zio" %% "zio-test-magnolia" % zioVersion % Test
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )