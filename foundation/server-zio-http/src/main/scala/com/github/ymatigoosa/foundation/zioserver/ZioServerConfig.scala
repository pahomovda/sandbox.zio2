package com.github.ymatigoosa.foundation.zioserver

import zio.config.*
import zio.config.magnolia.*

case class ZioServerConfig(
  server: Map[String, ZioServerConfig.ZioServerConfigNamed],
)

object ZioServerConfig {
  case class ZioServerConfigNamed(port: Int)

  given ConfigDescriptor[ZioServerConfigNamed] = descriptor
  given ConfigDescriptor[ZioServerConfig] = descriptor
}
