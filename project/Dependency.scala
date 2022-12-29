import sbt._

object Dependency {
  private object Version {
    val zio = "2.0.5"
    val zioJson = "0.4.2"
    val tapir = "1.2.4"
  }

  final val zio = List(
    "dev.zio" %% "zio" % Version.zio,
    "dev.zio" %% "zio-streams" % Version.zio,
  )

  final val zioTest = List(
    "dev.zio" %% "zio-test" % Version.zio % Test,
    "dev.zio" %% "zio-test-sbt" % Version.zio % Test,
    "dev.zio" %% "zio-test-junit" % Version.zio % Test,
    "dev.zio" %% "zio-test-magnolia" % Version.zio % Test,
  )

  final val tapirCore = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-core" % Version.tapir,
  )

  final val tapirZio = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-zio" % Version.tapir,
  )

  final val tapirZioHttpServer = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % Version.tapir,
  )

  final val tapirJsonZio = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-json-zio" % Version.tapir,
  )

  final val zioJson = Seq(
    "dev.zio" %% "zio-json" % Version.zioJson,
  )
}
