package com.github.ymatigoosa.foundation.routes

import sttp.tapir.*

object Headers {
  final val traceparent =
    header[Option[String]]("traceparent")
      .example(Some("00-0af7651916cd43dd8448eb211c80319c-b9c7c989f97918e1-01"))
      .description(
        "Identifies the incoming request in a tracing system. It has four parts: version, trace-id, parent-id, trace-flags",
      )
      .default(Some("00-0af7651916cd43dd8448eb211c80319c-b9c7c989f97918e1-01"))

  final val tracestate =
    header[Option[String]]("tracestate")
      .example(Some("rojo=00f067aa0ba902b7,congo=t61rcWkgMzE"))
      .description(
        "Companion header for the traceparent field. Provides additional vendor-specific trace identification information across different distributed tracing systems",
      )

  final val baggage =
    header[List[String]]("baggage")
      .example(List("userId=alice,serverNode=DF%2028,isProduction=false"))
      .description("represents a set of user-defined properties associated with a distributed request")
      .default(Nil)

  final val clientName =
    header[String]("x-client-name")
      .default("manual")

  final val clientVersion =
    header[Option[String]]("x-client-version")

  final val traceId =
    header[String]("x-trace-id")
      .example("0af7651916cd43dd8448eb211c80319c")

  final val commonInput =
    traceparent
      .and(tracestate)
      .and(baggage)
      .and(clientName)
      .and(clientVersion)
      .mapTo[CommonIn]

  final val commonOutput =
    traceId
      .mapTo[CommonOut]

  case class CommonIn(
    traceparent: Option[String],
    tracestate: Option[String],
    clientName: String,
    clientVersion: Option[String],
  )

  case class CommonOut(
    traceId: String,
  )
}
