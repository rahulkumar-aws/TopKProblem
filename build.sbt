import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.reactiveapp",
      scalaVersion := "2.11.12",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "TopKProblem",
    libraryDependencies += StreamLib,
    libraryDependencies += algebirdCore,
    libraryDependencies += guava,
    libraryDependencies += scalaTest % Test
  )




