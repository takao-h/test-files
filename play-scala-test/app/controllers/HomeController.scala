package controllers

import java.io.File
import javax.inject._

import play.api.data.Form
import play.api.i18n.{Lang, MessagesApi}
import play.api.mvc._
import play.i18n.Langs


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
    import java.io.{FileOutputStream => FileStream, OutputStreamWriter => StreamWriter};

    val file = new File("hogehoge.csv")
    val encode = "UTF-8"
    val append = true

    // 書き込み処理
    val fileOutPutStream = new FileStream(file, append)
    val writer = new StreamWriter(fileOutPutStream, encode)
    writer.write("あいうえお\r\n")
    writer.write("かきくけこ\r\n")
    writer.write("さしすせそ\r\n")
    writer.close

    Ok.sendFile(file)
  }

  import play.api.http.HttpErrorHandler
  import play.api.mvc._
  import scala.concurrent._
  import javax.inject.Singleton

  @Singleton
  class ErrorHandler extends HttpErrorHandler {

    def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
      Future.successful(
        Status(statusCode)("A client error occurred: " + message)
      )
    }

    def onServerError(request: RequestHeader, exception: Throwable) = {
      Future.successful(
        InternalServerError("A server error occurred: " + exception.getMessage)
      )
    }
  }

  import javax.inject._

  import play.api.http.DefaultHttpErrorHandler
  import play.api._
  import play.api.mvc._
  import play.api.routing.Router
  import scala.concurrent._

  @Singleton
  class ErrorHandlerw @Inject()(
                                 env: Environment,
                                 config: Configuration,
                                 sourceMapper: OptionalSourceMapper,
                                 router: Provider[Router]
                               ) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

    override def onProdServerError(request: RequestHeader, exception: UsefulException) = {
      Future.successful(
        InternalServerError("A server error occurred: " + exception.getMessage)
      )
    }

    override def onForbidden(request: RequestHeader, message: String) = {
      Future.successful(
        Forbidden("You're not allowed to access this resource.")
      )
    }
  }



}
