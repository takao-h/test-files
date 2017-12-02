val (x, y) = (1, 2)

(x, y) match {
  case (1, 2) => "one two"
  case _ => "what?"
}

val piyo : String = "hogehoge"
piyo match {
  case p if p.length < 5 => "sort"
  case p if p.length >= 5 => "long"
}

val p = "hoge"
val h = "hogehoge"

(p, h) match {
  case ("hoge", "hogehoge") => "hoge, hogehoge"
  case ("hoge", _) => "hoge, something"
  case (_, "hogehoge") => "something , hogehoge"
  case (_, _) => "something, something"
}

