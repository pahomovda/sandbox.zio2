package com.github.ymatigoosa.foundation.cfg

import com.typesafe.config.{Config, ConfigFactory}
import zio.*

final class InitialConfig(val value: Config)

object InitialConfig {
  val layer: ZLayer[Any, Throwable, InitialConfig] =
    ZLayer.fromZIO(ZIO.attempt(ConfigFactory.load()).map(i => InitialConfig(i)))
}
