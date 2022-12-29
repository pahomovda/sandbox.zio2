import sbt._
import sbt.Keys._

object Settings {
  val zioTest = Seq(
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )
}
