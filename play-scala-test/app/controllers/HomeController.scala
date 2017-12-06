package controllers

import java.io.File
import javax.inject._

import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def download = Action {

    import java.io.{ FileOutputStream=>FileStream, OutputStreamWriter=>StreamWriter };

    val file = new File("test.csv")
    val encode = "UTF-8"
    val append = true

    // 書き込み処理
    val fileOutPutStream = new FileStream(file, append)
    val writer = new StreamWriter( fileOutPutStream, encode )

    writer.write("あいうえお\r\n")
    writer.write("かきくけこ\r\n")
    writer.write("さしすせそ\r\n")
    writer.close

    Ok.sendFile(file)
  }

}
