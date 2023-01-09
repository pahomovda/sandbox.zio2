package com.github.ymatigoosa.foundation.cfg.impl

import com.github.ymatigoosa.foundation.cfg.{Cfg, InitialConfig, MkCfg}
import zio.*
import zio.config.*
import zio.config.typesafe.*

final class SimpleMkCfg(initialConfigSource: ConfigSource) extends MkCfg {
  def mk[A](path: String)(using descriptor: ConfigDescriptor[A]): Task[Cfg[A]] = {
    read(descriptor.from(initialConfigSource.at(PropertyTreePath.$(path))))
      .map(value => new ConstCfg(value))
  }

  def reloadable[A](path: String)(using descriptor: ConfigDescriptor[A]): Task[Cfg[A]] = {
    static(path).map(value => new ConstCfg(value))
  }

  def static[A](path: String)(using descriptor: ConfigDescriptor[A]): Task[A] = {
    read(descriptor.from(initialConfigSource.at(PropertyTreePath.$(path))))
  }
}

object SimpleMkCfg {
  val layer: ZLayer[InitialConfig, Throwable, MkCfg] =
    ZLayer {
      for {
        initialConfig <- ZIO.service[InitialConfig]
        initialConfigSource <- TypesafeConfigSource.fromTypesafeConfig(ZIO.succeed(initialConfig.value)).strictlyOnce
      } yield {
        new SimpleMkCfg(initialConfigSource)
      }
    }
}
