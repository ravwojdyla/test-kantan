organization := "sh.rav"
name := "test-kantan"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.8"

val kantanVersion = "0.1.17"

libraryDependencies ++= Seq(
  "com.nrinaudo" %% "kantan.csv" % kantanVersion,
  "com.nrinaudo" %% "kantan.csv-generic" % kantanVersion,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
  
