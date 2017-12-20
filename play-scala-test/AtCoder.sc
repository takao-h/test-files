//EXERCISE 2.1  n番目のフィボナッチ数を取得する再帰関数を記述せよ

// TODO: フィボナッチ数の公式を表すdefを作る

//自分で調べたやつ
def fib (l: Int)={
  var list = List[Int]()
  var a = 0
  var b = 1

  while (b<1){
    list :+= b
    val c = a
    a = b
    b = c+b
  }
  list
}


//　回答

def fib(n: BigInt): BigInt = {
  @annotation.tailrec
  def loop(n: BigInt, prev: BigInt, cur: BigInt): BigInt = {
    if (n == 0) prev else loop(n - 1, cur, prev + cur)
  }
  loop(n, 0, 1)
}



//EXERCISE 2.2 Array[A] がソートされているかを調べる isSorted を実装せよ

// index(n)の値が必ずindex(n+1)よりも小さくindex(n-1)よりも大きい？

/**わからぬ2017/12/04*/

//回答

object Scala extends App {
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    as.sortWith(ordered).toSeq == as.toSeq
  }

  println(isSorted(Array(1, 2, 3), (x: Int, y: Int) => x < y))  // true
  println(isSorted(Array(2, 3, 1), (x: Int, y: Int) => x < y))  // false
  println(isSorted(Array("A", "BB", "CCC"), (x: String, y: String) => x.length < y.length))  // true
  println(isSorted(Array("BB", "CCC", "A"), (x: String, y: String) => x.length < y.length))  // false
}

//本家
def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
  @annotation.tailrec
  def go(n: Int): Boolean =
    if (n >= as.length-1) true
    else if (gt(as(n), as(n+1))) false
    else go(n+1)

  go(0)
}

//カリー化の実装

/**わからぬ2017/12/04*/

//お手本
object Curry extends App {
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)

  println(curry((_: Int) + (_: Int))(2)(3))  // 5
  println(curry((_: String).charAt(_: Int))("ABCDE")(2))  // C
}