package com.github.ymatigoosa.foundation.zioserver

import com.github.ymatigoosa.foundation.cfg.{InitialConfig, MkCfg}
import zio.*
import zio.http.*
import zio.http.model.Method
import sttp.capabilities.zio.ZioStreams
import sttp.tapir.server.interceptor.Interceptor
import sttp.tapir.ztapir.*
import sttp.tapir.server.ziohttp.{ZioHttpInterpreter, ZioHttpServerOptions}

import java.net.InetSocketAddress

object ZioServer {

  def mk[A: Tag](name: String, endpoints: List[ZServerEndpoint[Any, ZioStreams]], createAs: Int => A) = {
    mkServerConfigLayer(name) >>> Server.live >>> mkHttpAppLayer(endpoints, createAs)
  }

  private def mkServerConfigLayer(name: String) = ZLayer {
    for {
      mkCfg <- ZIO.service[MkCfg]
      appServerConfig <- mkCfg.static[ZioServerConfig]("app.zio-http")
      namedConfig <- ZIO.attempt(
        appServerConfig.server.getOrElse(name, throw new RuntimeException(s"missing config for server $name")),
      )
    } yield {
      ServerConfig().copy(
        address = new InetSocketAddress(namedConfig.port),
      )
    }
  }

  private def mkHttpAppLayer[A: Tag](
    endpoints: List[ZServerEndpoint[Any, ZioStreams]],
    createAs: Int => A,
  ) = ZLayer {
    val interpreterOptions = mkZioHttpServerOptions()
    val serverInterpreter = ZioHttpInterpreter(interpreterOptions)
    val app = serverInterpreter.toHttp(endpoints)

    for {
      server <- ZIO.service[Server]
      _ <- server.install(app)
    } yield createAs(server.port)
  }

  private def mkZioHttpServerOptions() = {
    val interceptors: List[Interceptor[RIO[Any, *]]] = Nil
    val initial = ZioHttpServerOptions.default[Any]
    val withInterceptors =
      interceptors.foldLeft(initial) { case (acc, i) => acc.appendInterceptor(i) }

    withInterceptors
  }
}
