import sbt._
import sbt.Keys._

object Settings {
  final val zioTest = Seq(
    libraryDependencies ++= Dependency.zioTest,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
}
