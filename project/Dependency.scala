import sbt._

object Dependency {
  private object Version {
    val zio = "2.0.5"
  }

  final val zio = List(
    "dev.zio" %% "zio" % Version.zio,
  )

  final val zioTest = List(
    "dev.zio" %% "zio-test" % Version.zio % Test,
    "dev.zio" %% "zio-test-sbt" % Version.zio % Test,
    "dev.zio" %% "zio-test-junit" % Version.zio % Test,
    "dev.zio" %% "zio-test-magnolia" % Version.zio % Test
  )

  final val zioAll = zio ++ zioTest
}
