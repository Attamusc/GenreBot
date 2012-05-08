package controllers

import play.api._
import play.api.mvc._
import play.api.libs.Files
import play.api.libs.json._
import java.io.File
import dispatch._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def genreParse = Action(parse.multipartFormData) { request =>
    var rock_percentage : Float = 0.0f
    var electronic_percentage : Float = 0.0f

    // Grab files from our request
    request.body.files.map { file =>
      val path = "/tmp/" 
      val filename = path + file.filename.replaceAll("""\s+""", "-")
      Files.copyFile(file.ref.file, new File(filename))
      
      // Make a remote request to our tornado server
      val h = new Http
      val resp = h(url("http://localhost:8888/parse?filename=" + filename) as_str)

      val json_resp = Json.parse(resp)

      rock_percentage = (json_resp \ "rock").as[Float]
      electronic_percentage = (json_resp \ "electronic").as[Float]
    }

    Ok(views.html.result(rock_percentage, electronic_percentage))
  }

  def resultTest = Action {
    Ok(views.html.result(0.5f, 0.5f))
  }
  
}