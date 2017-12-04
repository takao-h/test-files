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

