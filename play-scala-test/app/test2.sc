import controllers.routes
import play.api.data._
import play.api.data.Forms._

case class UserData(name: String, age: Int)

val userFormConstraints2 = Form(
  mapping(
    "name" -> nonEmptyText,
    "age" -> number(min = 0, max = 100)
  )(UserData.apply)(UserData.unapply)
)

val boundForm = userFormConstraints2.bind(Map("bob" -> "", "age" -> "25"))
boundForm.hasErrors



def validate(name: String, age: Int) = {
  name match {
    case "bob" if age >= 18 =>
      Some(UserData(name, age))
    case "admin" =>
      Some(UserData(name, age))
    case _ =>
      None
  }
}

val userFormConstraintsAdHoc = Form(
  mapping(
    "name" -> text,
    "age" -> number
  )(UserData.apply)(UserData.unapply) verifying("Failed form constraints!", fields => fields match {
    case userData => validate(userData.name, userData.age).isDefined
  })
)
