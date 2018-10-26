import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.3"
  lazy val algebirdCore = "com.twitter" %% "algebird-core" % "0.13.3"
  lazy val StreamLib = "com.clearspring.analytics" % "stream" % "2.9.5"
  lazy val guava = "com.google.guava" % "guava" % "23.5-jre"

}

