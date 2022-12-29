import sbt._
import sbt.Keys._

object ProjectKindServiceApp extends AutoPlugin {
  override def requires: Plugins = super.requires

  override val projectSettings: Seq[Def.Setting[_]] =
    super.projectSettings ++ Settings.zioTest
}

object ProjectKindServiceApi extends AutoPlugin {
  override def requires: Plugins = super.requires

  override val projectSettings: Seq[Def.Setting[_]] =
    super.projectSettings ++ Settings.zioTest
}
