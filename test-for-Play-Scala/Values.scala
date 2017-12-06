case class Values(value: Int)

object Values {
  implicit val value = Json.format[Values]
}

case class Data1(name: String, period: String, values: Seq[Values], title: String, description: String, id: String)

object Data1 {
  implicit val data = Json.format[Data1]
}

case class PostInsights(data: Seq[Data1])

object PostInsights {
  implicit val postInsights = Json.format[PostInsights]
}