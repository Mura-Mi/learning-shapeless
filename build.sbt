name := "learning-shapeless"
scalaVersion := "2.12.2"

libraryDependencies ++= Seq("com.chuusai" %% "shapeless" % "2.3.2")

initialCommands in console := """
import yokohama.murataku._
import shapeless._
"""
