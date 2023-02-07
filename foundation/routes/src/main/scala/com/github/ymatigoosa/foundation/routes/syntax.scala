package com.github.ymatigoosa.foundation.routes

import sttp.tapir.*

object syntax {
  extension [A, I, E, O, R](e: Endpoint[A, I, E, O, R]) {
    def appCommonHeaders: Endpoint[A, I, E, O, R] = {
      e.in(Headers.commonInput)
        .out(Headers.commonOutput)
    }

    def appZonePublic: Endpoint[A, I, E, O, R] = applyZone("public")
    def appZonePrivate: Endpoint[A, I, E, O, R] = applyZone("private")
    def appZoneDebug: Endpoint[A, I, E, O, R] = applyZone("debug")
    def appZoneSupport: Endpoint[A, I, E, O, R] = applyZone("support")

    def appJwtAuth: Endpoint[A, I, E, O, R] = {
      e.in(Auth.jwt)
    }

    private def applyZone(name: String) = {
      e.prependIn(name)
    }
  }
}
