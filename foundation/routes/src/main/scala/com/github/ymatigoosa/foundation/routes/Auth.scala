package com.github.ymatigoosa.foundation.routes

import sttp.tapir.*

object Auth {
  final val jwt = auth.bearer[String]().mapTo[Jwt]

  case class Jwt(rawJwt: String) extends AnyVal
}
