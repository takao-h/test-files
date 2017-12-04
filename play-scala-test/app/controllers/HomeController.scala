package controllers

import java.io.File
import javax.inject._

import play.api._
import play.api.mvc._

import scala.reflect.io.Path

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

//  def csv(id: String) = Action {
//    val path = Path("temp/csv/" + id + ".csv")
//    path.write("１行目")
//    path.write("２行目")
//    path.write("３行目")
//    val file = new File("temp/csv/" + id + ".csv")
//    Ok.sendFile(file)
//  }

}
