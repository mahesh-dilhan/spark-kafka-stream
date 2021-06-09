name := "spark-kafka-stream"

version := "0.1"

scalaVersion := "2.13.6"

idePackagePrefix := Some("io.mahesh")

val spark = "org.apache.spark" % "spark-core_2.12" % "3.1.2"
val sparkStreaming = "org.apache.spark" % "spark-streaming_2.12" % "3.1.2"
val sparkStreamKafka = "org.apache.spark" % "spark-streaming-kafka-0-10_2.12" % "3.1.2"


lazy val root = (project in file(".")).
  settings(
    libraryDependencies += spark,
    libraryDependencies += sparkStreaming,
    libraryDependencies += sparkStreamKafka
  )