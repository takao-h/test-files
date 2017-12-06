val (x, y) = (1, 2)

(x, y) match {
  case (1, 2) => "one two"
  case _ => "what?"
}

val piyo: String = "hogehoge"
piyo match {
  case p if p.length < 5 => "sort"
  case p if p.length >= 5 => "long"
}

val p = "piyo"
val h = "hogeh"

// tuple
(p, h) match {
  case ("piyo", "hogehoge") => "hoge, hogehoge"
  case ("piyo", _) => "hoge, something"
  case (_, "hogehoge") => "something , hogehoge"
  case (_, _) => "something, something"
}

// regular expression
//val re1 = "piyo\d+".r
//"piyo123" match {
//  case re1() => "match"
//  case _ => "mismatch"
//}
//
//val re2 = "piyo(\d+)hoge(\d+)".r
//"piyo123hoge456" match {
//  case re2(p, h) => p + h
//  case _ => "mismatch"
//}
//
//val re3 = "piyo(\d+)".r.unanchored
//"hogepiyo123hoge" match {
//  case re3(num) =>num
//  case _ => "mismatch"
//}

// Option
val piyopiyo: Option[String] = Some("piyo")

piyopiyo match {
  case Some(piyopiyo) => println(piyopiyo)
  case None => "nothing"
}

//class Piyo(val piyo: String, val hoge: String)
//object Piyo{
//  def unapply(p: Piyo): Some(p.piyo)
//}
//
//val piyo1 = new Piyo("piyopiyo", "hogehoge")
//piyo1 match {
//  case Piyo(p) => p
//  case _ =>"not piyo"
//}

case class Hogehoge(p: String, h: String)

val hogehoge = Hogehoge("piyo", "hoge")
hogehoge match {
  case Hogehoge(p, h) => p + h
  case _ => "mismatch"
}


val list = List(1, 2, 3)

list match {
  case x :: xs => println(s"the head element is ${x}")
  case _ => println("no elements exist")
}


val result = 10 - 4
result match {
  case 6 => println ("six")
  case _ => println ("other")
}
