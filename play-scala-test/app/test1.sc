import java.io.PrintWriter

object Main extends App {
  val file = new PrintWriter("filename")
  file.write("hello world")
  file.close()
}

/**
  * Scalaでファイルの入出力
  * Javaのクラスを使ってファイルを作成し、Scalaのクラスを使ってファイルを読み込む
  */
object FileIO {
  def main( args:Array[String] ) {

    // JavaのFileOutputStreamクラスとOutputStreamWriterを別名でimport
    // それぞれインスタンス生成時の1回のみの使用なので別名にする意味は特にないです。
    import java.io.{ FileOutputStream=>FileStream, OutputStreamWriter=>StreamWriter };

    val fileName = "test.txt"
    val encode = "UTF-8"
    val append = true

    // 書き込み処理
    val fileOutPutStream = new FileStream(fileName, append)
    val writer = new StreamWriter( fileOutPutStream, encode )

    writer.write("あいうえお\r\n")
    writer.write("かきくけこ\r\n")
    writer.write("さしすせそ\r\n")
    writer.close


    // 読み込み処理(コンソールに出力)
    // 1行で書くとこんな感じ
    // scala.io.Source.fromFile(fileName, encode).getLines.foreach{ println _ }
    val source = scala.io.Source.fromFile(fileName, encode)
    val lines = source.getLines
    lines.foreach{ println _ }
  }
}



