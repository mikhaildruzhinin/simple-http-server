package ru.mikhaildruzhinin

import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}

import java.io.OutputStream
import java.net.InetSocketAddress

object SimpleHttpServer {
  def main(args: Array[String]): Unit = {
    val server = HttpServer.create(new InetSocketAddress(8080), 0)
    server.createContext("/", SimpleHttpHandler())
    server.setExecutor(null)
    server.start()
  }
}

class SimpleHttpHandler() extends HttpHandler {
  override def handle(httpExchange: HttpExchange): Unit = {
    val response = "Hello world"
    httpExchange.sendResponseHeaders(200, response.length)
    val outputStream: OutputStream = httpExchange.getResponseBody
    outputStream.write(response.getBytes)
    outputStream.close()
  }
}

object SimpleHttpHandler {
  def apply(): SimpleHttpHandler = new SimpleHttpHandler()
}