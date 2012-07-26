import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "play20-qunit"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      // Add your project dependencies here,
        "commons-io" % "commons-io" % "2.3",
        "play" % "play-test_2.9.1" % "2.0.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here
    )

}
