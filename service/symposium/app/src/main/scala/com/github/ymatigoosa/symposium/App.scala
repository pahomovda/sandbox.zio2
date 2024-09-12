package com.github.ymatigoosa.symposium

import zio.Console.ConsoleLive
import zio.{Console, Task, ZIOAppDefault}

object App extends ZIOAppDefault {

  def app(c: Console): Task[Unit] =
    for
      _ <- c.printLine("What is your name?")
      name <- c.readLine
      _ <- c.printLine(s"Hello $name!")
    yield ()

  def run = app(ConsoleLive)
}
