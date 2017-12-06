package com.example
import play.api.libs.json.Json


object Hello extends App {

  val data = """
{
  "id": "1000",
  "test": "hoge",
  "user": "Ajido"
  "data": {
    "key":  "value"
  }
}
"""
  val jsonObj = Json.parse(data)

  case class Message(id: String, user: String, data: Data)
  case class Data(key: String)

  implicit val formats = DefaultFormats
  val msg = jsonObj.extract[Message]

  println(msg)
  println(msg.id)
  println(msg.data.key)


  }
