name := "spark-kafka-stream"

version := "0.1"

scalaVersion := "2.12.13"

idePackagePrefix := Some("io.mahesh")

lazy val commonSettings = Seq(
  organization := "io.mahesh",
  version := "0.1.0",
  // set the Scala version used for the project.  2.11 isn't supported with Spark yet
  scalaVersion := "2.12.13"
)

val spark = "org.apache.spark" % "spark-core_2.12" % "3.1.2"
val sparkStreaming = "org.apache.spark" % "spark-streaming_2.12" % "3.1.2"
val sparkStreamKafka = "org.apache.spark" % "spark-streaming-kafka-0-10_2.12" % "3.1.2"


lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "spark-kafka-stream"
  ).
  settings(
    libraryDependencies += spark,
    libraryDependencies += sparkStreaming,
    libraryDependencies += sparkStreamKafka
  )