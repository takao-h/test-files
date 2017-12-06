
5 >> 2

7 << 1

case class User(id: Int, name: String)

User.getClass.getDeclaredFields

val user = User(1234567890, "hayashi")
val newUser = user.copy(id = 987654321)

classOf[User].getDeclaredFields

case class AccoutInfo(access_token: String, category: String, name: String, id: String, perms: Seq[String])

AccoutInfo.getClass.getDeclaredFields

case class UserRow(email: String, password: String, keywords: Option[String] = None, instagramId: Option[String] = None, instagramBusinessId: Option[String] = None, instagramAccessToken: Option[String] = None, facebookId: Option[String] = None, facebookAccessToken: Option[String] = None, twitterId: Option[String] = None, twitterAccessToken: Option[String] = None, id: Option[Int] = None)


val (x, y) = (1, 2) //分解して代入 x = 1, y = 2

(x, y) match {
  case (1, 2) => "one, two" //パターンで分岐
  case _ => "what?"
}


case class Piyo(p: String, h: String)

val piyo = Piyo("piyo", "hoge") //applyも定義されるのでnew不要
piyo match {
  case Piyo(p, h) => p + h // "piyohoge"
  case _ => "mismatch"
}

//
////代入もできる
//val Piyo(p, h) = piyo //p = "piyo", h = "hoge"


case class AccoutInf(access_token: String, category: String, name: String, id: String)

implicit val accoutInf = AccoutInf("123456789", "higeige", "huag", "0000000")

println(classOf[AccoutInf].getDeclaredFields.map(_.getName).mkString(","))


case class A(val i: Int, val c: String) {
  override def toString = A.unapply(this).get.toString // TODO: apply proper formatting.
}

val a = A(5, "Hello world")
println(a.toString)






val f: (Int) => String = new Function1[Int, String] {
  def apply(arg: Int): String = arg.toString
}
f.apply(10)





case class Person(val name: String, val age: Int)
val a2 = Person("taro", 30)
val b = Person("hanako", 20)
val c = Person("yoshio", 10)
val pList = List(a2, b, c)
for (p <- pList) {
  p match {
    case Person(_, 10) => println("yoshio")
    case Person("taro", _) => println("taro")
    case Person(name, age) => println("who?")
  }

}


case class B(i: Int, j: Int, k: Int)
val b1 = B(123, 456, 789)
val b2 = b1.copy()

val list = List(3,4,5)
for (i <- list) println(i)
