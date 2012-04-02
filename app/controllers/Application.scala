package controllers

import play.api._
import play.api.mvc._
import play.api.libs.Files

import java.io.File

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }

  def about = Action {
    Ok(views.html.about())
  }

  def genreParse = Action(parse.multipartFormData) { request =>
    request.body.files.map { file =>
      val path = "/tmp/" 
      val filename = path + file.filename
      Files.copyFile(file.ref.file, new File(filename)) 
    }

    Ok("File Uploaded")
  }
  
}