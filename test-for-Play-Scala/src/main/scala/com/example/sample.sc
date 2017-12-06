import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

  val msg: String = "hello"
  val f: Future[String] = Future {
    Thread.sleep(1000)
    msg * 2
  }
  //    f.onSuccess { case msg: String => println(msg) }
  //    f.onFailure { case t: Throwable => println(t.getMessage()) }
  f.onComplete {
    case Success(msg) => println(msg)
    case Failure(t)   => println(t.getMessage())
  }
  Await.ready(f, Duration.Inf)



val f1: Future[Int] = Future{ Thread.sleep(3000); 1 }
val f2: Future[Int] = Future{ Thread.sleep(2000); 2 }

val fff: Future[Int] = f1.flatMap{ n1: Int =>
  val ff: Future[String] = f2.map{ n2: Int => "hello" * (n1 + n2) }
  if (Await.result(ff, Duration.Inf).size > 10) ff else Future.failed(new Exception("not over 10"))
}.map { str: String =>
  str.size
}

fff.onComplete {
  case Success(result) => println(result)
  case Failure(t)      => println(t.getMessage())
}

Await.ready(f, Duration.Inf)


val hoge: Option[String] =""
plintln(hoge.)