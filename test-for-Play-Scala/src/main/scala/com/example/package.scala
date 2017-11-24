package com

/**
  * Created by ruth on 2017/11/23.
  */
package object example {

  case class Hokutoshinken(name: String, age: Int, deathblow: String, family: Seq[Brother])

  Object Hokutoshinken {
    implicit val hokutosinken = (
      (__ \ "name").format[String] ~
        (__ \ "age").format[Int] ~
        (__ \ "deathblow").format[String] ~
        (__ \ "family").format[Seq[Brother]]
      ) (hokutosinken.apply _, unlift(hokutosinken.unapply _))
  }

  case class Brother(name: String, deathblow: String)

  Object Brother {
    implicit val brother = (
      (__ \ "name").format[String] ~
        (__ \ "deathblow").format[String]
      ) (Brother.apply _, unlift(Brother.unapply _))
  }

  object hoge extends App {
    val hokutonoken =
      """
          {
            name:"ケンシロウ"
            age:26
            deathblow:"無想転生"
            family: [
              {
                name:"ラオウ"
                deathblow:"天将奔烈"
              },
              {
                name:"トキ"
                deathblow:"天翔百裂拳"
              },
              {
                name:"ジャギ"
                deathblow:"北斗羅漢撃"
              }
            ]
          }
      """.stripMargin

    val json = Json.parse(hokutonoken)
    val hokutosinken = json.validate[Hokutoshinken].get // ここでerror

    //ストリームからscala classへ変換したものをprint
    println(hokutosinken)

    // scala classをjsonへ変換
    println(Json.stringify(Json.toJson(hokutosinken)))
  }

}
