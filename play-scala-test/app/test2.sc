import controllers.routes
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.Action

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





import play.api.libs.json._

object PlayJsonEitherInstances {

  implicit def eitherReads[A, B](implicit A: Reads[A], B: Reads[B]): Reads[Either[A, B]] =
    Reads[Either[A, B]] { json =>
      A.reads(json) match {
        case JsSuccess(value, path) =>
          JsSuccess(Left(value), path)
        case JsError(e1) => B.reads(json) match {
          case JsSuccess(value, path) =>
            JsSuccess(Right(value), path)
          case JsError(e2) =>
            JsError(JsError.merge(e1, e2))
        }
      }
    }

  implicit def eitherWrites[A, B](implicit A: Writes[A], B: Writes[B]): Writes[Either[A, B]] =
    Writes{
      case Left(a) => A.writes(a)
      case Right(b) => B.writes(b)
    }

  implicit def eitherOWrites[A, B](implicit A: OWrites[A], B: OWrites[B]): OWrites[Either[A, B]] =
    OWrites{
      case Left(a) => A.writes(a)
      case Right(b) => B.writes(b)
    }

}




def eitherReads[A, B](implicit A: Reads[A], B: Reads[B]): Reads[Either[A, B]] =
  Reads{ json =>
    (A.reads(json), B.reads(json)) match {
      case (JsSuccess(l, _), _: JsError) =>
        JsSuccess(Left(l))
      case (_: JsError, JsSuccess(r, _)) =>
        JsSuccess(Right(r))
      case (JsSuccess(_, _), JsSuccess(_, _)) =>
        JsError("両方成功しちゃったのでエラーにしたよ(・ω<)") // ここのメッセージはご自由に。引数でとるようにしてもいいかも
      case (e1: JsError, e2: JsError) =>
        e1 ++ e2
    }
  }

val oyatsu: Option[Int] = None
val message = oyatsu.fold("おやつは持ってきちゃダメ！") { money => s"おやつは${money}円までです"}



class Sample5 {
  type A = Int

  def flatMap(f: A => Array[A]) = {
    import scala.collection.mutable._
    val a = ArrayBuffer[A]()
    a ++= f(11)
    a ++= f(22)
    a ++= f(33)
    a
  }
  def map(f: A => A) = {
    Array[A](f(1), f(2), f(3))
  }
}
for (i <- new Sample5;
     j <- new Sample5) yield {
  i + j
}


sealed trait LoginError
// パスワードが間違っている場合のエラー
case object InvalidPassword extends LoginError
// nameで指定されたユーザーが見つからない場合のエラー
case object UserNotFound extends LoginError
// パスワードがロックされている場合のエラー
case object PasswordLocked extends LoginError

case class User(id: Long, name: String, password: String)

object LoginService {
  def login(name: String, password: String): Either[LoginError, User] = ???
}

LoginService.login(name = "dwango", password = "password") match {
  case Right(user) => println(s"id: ${user.id}")
  case Left(InvalidPassword) => println(s"Invalid Password!")
}


object MainRefactored {

  case class Address(id: Int, name: String, postalCode: Option[String])
  case class User(id: Int, name: String, addressId: Option[Int])

  val userDatabase: Map[Int, User] = Map (
    1 -> User(1, "太郎", Some(1)),
    2 -> User(2, "二郎", Some(2)),
    3 -> User(3, "プー太郎", None)
  )

  val addressDatabase: Map[Int, Address] = Map (
    1 -> Address(1, "渋谷", Some("150-0002")),
    2 -> Address(2, "国際宇宙ステーション", None)
  )

  sealed abstract class PostalCodeResult
  case class Success(postalCode: String) extends PostalCodeResult
  abstract class Failure extends PostalCodeResult
  case object UserNotFound extends Failure
  case object UserNotHasAddress extends Failure
  case object AddressNotFound extends Failure
  case object AddressNotHasPostalCode extends Failure

  // 本質的に何をしているかわかりやすくリファクタリング
  def getPostalCodeResult(userId: Int): PostalCodeResult = {
    (for {
      user <- findUser(userId)
      address <- findAddress(user)
      postalCode <- findPostalCode(address)
    } yield Success(postalCode))
  }

  def findUser(userId: Int): Either[Failure, User] = {
    userDatabase.get(userId).toRight(UserNotFound)
  }

  def findAddress(user: User): Either[Failure, Address] = {
    for {
      addressId <- user.addressId.toRight(UserNotHasAddress)
      address <- addressDatabase.get(addressId).toRight(AddressNotFound)
    } yield address
  }

  def findPostalCode(address: Address): Either[Failure, String] = {
    address.postalCode.toRight(AddressNotHasPostalCode)
  }

  def main(args: Array[String]): Unit = {
    println(getPostalCodeResult(1)) // Success(150-0002)
    println(getPostalCodeResult(2)) // AddressNotHasPostalCode
    println(getPostalCodeResult(3)) // UserNotHasAddress
    println(getPostalCodeResult(4)) // UserNotFound
  }
}



object OptionEither {
  def main(args: Array[String]): Unit = {
    println("Please enter word.")
    val word = scala.io.StdIn.readLine
    validateByOption(word)
    validateByEither(word)
  }
  def validateByOption(word: String): Option[String] = {
    word.contains(' ') match {
      case true => None
      case false => Some(word)
    }
  }
  def validateByEither(word: String): Either[String, String] = {
    word.contains(' ') match {
      case true => Left("Error The Word Contains Space")
      case false => Right(word)
    }
  }
}