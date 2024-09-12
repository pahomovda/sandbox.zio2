package com.github.ymatigoosa.foundation.cfg

import zio.UIO

trait Cfg[A] {
  def value: UIO[A]
}
