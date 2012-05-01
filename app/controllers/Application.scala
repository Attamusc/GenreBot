package controllers

import play.api._
import play.api.mvc._
import play.api.libs.Files
import play.api.libs.json

import java.io.File

import dispatch._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }

  def genreParse = Action(parse.multipartFormData) { request =>
    request.body.files.map { file =>
      val path = "/tmp/" 
      val filename = path + file.filename
      Files.copyFile(file.ref.file, new File(filename))
      
      // Make a remote request to our tornado server
      val h = new Http
      val resp = h(url("http://localhost:8888/parse?filename=" + file.filename) as_str)

      //val json_test = Json.parse(resp)

      Logger.info(resp)
    }

    Ok("File Uploaded")
  }
  
}