ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "newsapietl",
    idePackagePrefix := Some("dev.jszafran")
  )

libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.6.2"
libraryDependencies += "com.lihaoyi" %% "requests" % "0.7.0"
libraryDependencies += "com.lihaoyi" %% "ujson" % "0.9.6"
libraryDependencies += "au.com.bytecode" % "opencsv" % "2.4"
