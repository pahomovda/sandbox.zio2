package com.github.ymatigoosa.todolist.api

import sttp.tapir.*
import zio.json.*
import sttp.tapir.json.zio.*

object TodoListEndpointsPublic {
  final val todoList =
    endpoint.get
      .in("hello")
      .in(path[String]("name"))
      .out(stringBody)
}
