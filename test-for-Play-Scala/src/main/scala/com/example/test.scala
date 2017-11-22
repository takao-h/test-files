package com.example

class test {
  object ComplicatedReadsCombinator extends App {
    val rawString =
      """
        |{
        |  "name": "Alice",
        |  "age": 20,
        |  "favorites": ["tennis", "swimming"],
        |  "family": [
        |    {
        |      "name": "Bob",
        |      "relation": "father"
        |    },
        |    {
        |      "name": "Catharin",
        |      "relation": "mother"
        |    }
        |  ]
        |}
      """.stripMargin

    val json = Json.parse(rawString)
    val person = json.validate[com.tiqwab.example.json.model.Person].get
    println(person) // Person(Alice,20,List(tennis, swimming),List(FamilyMember(Bob,father), FamilyMember(Catharin,mother)))
    println(Json.stringify(Json.toJson(person))) // {"name":"Alice","age":20,"favorites":["tennis","swimming"],"family":[{"name":"Bob","relation":"father"},{"name":"Catharin","relation":"mother"}]}
  }
}
