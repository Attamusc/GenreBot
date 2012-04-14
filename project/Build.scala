import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "genrebot"
    val appVersion      = "0.1"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "postgresql" % "postgresql" % "8.4-702.jdbc4",
      "net.databinder" %% "dispatch-http" % "0.8.8"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
