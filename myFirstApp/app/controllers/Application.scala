package controllers

import play.api._
import play.api.mvc._
import models.Quote

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready.", Quote("A curiosidade é mais importante do que o conhecimento.", "Albert Einstein")))
  }

}