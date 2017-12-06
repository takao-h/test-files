package controllers

import play.api.mvc._

class Test extends Controller {

  def index = Action {
    Ok("It works!")
  }

}