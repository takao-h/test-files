package com.example

import com.sun.tools.javac.jvm.Profile

object Hello extends App {

  val str = """
    {
      "name": "miyatin",
      "age": 21,
      "lang": ["Scala", "C#", "JavaScript", "PHP"]
      "univ": {
        "name": "Kobe Univ",
        "major": "Engineering",
        "grade": 4
      }
    }
  """

  // 文字列からProfileクラスへ
  val profile: Profile = Json.parse(str).validate[Profile]

  // ProfileクラスからJSONへ
  val profileJson = Json.toJson(profile)

}