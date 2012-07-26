import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "play20-qunit-sample"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      // Add your project dependencies here,
        "play20-qunit" % "play20-qunit_2.9.1" % "1.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here
    )

}
