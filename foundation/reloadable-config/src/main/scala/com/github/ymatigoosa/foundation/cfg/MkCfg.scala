package com.github.ymatigoosa.foundation.cfg
import zio.*
import zio.config.*
import zio.config.magnolia.*
import com.github.ymatigoosa.foundation.cfg.impl

trait MkCfg {
  def mk[A](path: String)(using descriptor: ConfigDescriptor[A]): Task[Cfg[A]]
}

object MkCfg {
  val simple: ZLayer[InitialConfig, Throwable, MkCfg] = impl.SimpleMkCfg.layer
  val reloadable: ZLayer[InitialConfig, Throwable, MkCfg] = ???
}
