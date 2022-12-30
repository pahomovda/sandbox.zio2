package com.github.ymatigoosa.foundation.cfg.impl

import com.github.ymatigoosa.foundation.cfg.Cfg
import zio.*

final class ConstCfg[A](staticValue: A) extends Cfg[A] {
  override val value: UIO[A] = ZIO.succeed(staticValue)
}
