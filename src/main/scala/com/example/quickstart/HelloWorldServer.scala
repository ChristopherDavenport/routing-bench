package com.example.quickstart

import cats.data.Kleisli
import cats.effect.IO
import fs2.StreamApp
import io.circe._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext.Implicits.global
import cats.syntax.all._
import org.http4s.syntax.all._

object HelloWorldServer extends StreamApp[IO] with Http4sDsl[IO] {
  val service = HttpService[IO] {
    case GET -> Root / "route1" / "route11" / name =>
      Ok()
    case GET -> Root / "route2" / "route22" / "route222" / name =>
      Ok()
  }

  val service2 = HttpService[IO] {
    case GET -> Root / "route3" / "route33" / name =>
      Ok()
    case GET -> Root / "route4" / "route44" / "route444" / name =>
      Ok()
  }

  val service3 = HttpService[IO] {
    case GET -> Root / "route5" / "route55" / name =>
      Ok()
    case GET -> Root / "route6" / "route66" / "route666" / name =>
      Ok()
  }

  val service4 = HttpService[IO] {
    case GET -> Root / "route7" / "route77" / name =>
      Ok()
    case GET -> Root / "route8" / "route88" / "route888" / name =>
      Ok()
  }

  val service5 = HttpService[IO] {
    case GET -> Root / "route9" / "route99" / name =>
      Ok()
    case GET -> Root / "route10" / "route1010" / "route101010" / name =>
      Ok()
  }

  val service6 = HttpService[IO] {
    case GET -> Root / "route11" / "route1111" / name =>
      Ok()
    case GET -> Root / "route12" / "route1212" / "route121212" / name =>
      Ok()
  }

  val service7 = HttpService[IO] {
    case GET -> Root / "route13" / "route13" / name =>
      Ok()
    case GET -> Root / "route14" / "route1414" / "route141414" / name =>
      Ok()
  }

  val service8 = HttpService[IO] {
    case GET -> Root / "route15" / "route1515" / name =>
      Ok()
    case GET -> Root / "route16" / "route1616" / "route161616" / name =>
      Ok()
  }

  val service9 = HttpService[IO] {
    case GET -> Root / "route17" / "route1717" / name =>
      Ok()
    case GET -> Root / "route18" / "route1818" / "route181818" / name =>
      Ok()
  }

  val service10 = HttpService[IO] {
    case GET -> Root / "route19" / "route1919" / name =>
      Ok()
    case GET -> Root / "route20" / "route2020" / "route202020" / name =>
      Ok()
  }

  val serviceBIG1 = HttpService[IO] {
    case GET -> Root / "route1" / "route11" / name =>
      Ok()
    case GET -> Root / "route2" / "route22" / "route222" / name =>
      Ok()
    case GET -> Root / "route3" / "route33" / name =>
      Ok()
    case GET -> Root / "route4" / "route44" / "route444" / name =>
      Ok()
    case GET -> Root / "route5" / "route55" / name =>
      Ok()
    case GET -> Root / "route6" / "route66" / "route666" / name =>
      Ok()
    case GET -> Root / "route7" / "route77" / name =>
      Ok()
    case GET -> Root / "route8" / "route88" / "route888" / name =>
      Ok()
    case GET -> Root / "route9" / "route99" / name =>
      Ok()
    case GET -> Root / "route10" / "route1010" / "route101010" / name =>
      Ok()
  }

  val serviceBIG2 = HttpService[IO] {
    case GET -> Root / "route11" / "route1111" / name =>
      Ok()
    case GET -> Root / "route12" / "route1212" / "route121212" / name =>
      Ok()
    case GET -> Root / "route13" / "route13" / name =>
      Ok()
    case GET -> Root / "route14" / "route1414" / "route141414" / name =>
      Ok()
    case GET -> Root / "route15" / "route1515" / name =>
      Ok()
    case GET -> Root / "route16" / "route1616" / "route161616" / name =>
      Ok()
    case GET -> Root / "route17" / "route1717" / name =>
      Ok()
    case GET -> Root / "route18" / "route1818" / "route181818" / name =>
      Ok()
    case GET -> Root / "route19" / "route1919" / name =>
      Ok()
    case GET -> Root / "route20" / "route2020" / "route202020" / name =>
      Ok()
  }

  val composedMany: HttpService[IO] =
    service <+> service2 <+>
      service3 <+> service4 <+>
      service5 <+> service6 <+>
      service7 <+> service8 <+>
      service9 <+> service10

  val CMany: Kleisli[IO, Request[IO], Response[IO]] = composedMany.orNotFound

  val composedBig2: HttpService[IO] = serviceBIG1 <+> serviceBIG2

  val CTwo: Kleisli[IO, Request[IO], Response[IO]] = composedBig2.orNotFound

  def stream(args: List[String], requestShutdown: IO[Unit]) =
    BlazeBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .mountService(service, "/")
      .serve
}
