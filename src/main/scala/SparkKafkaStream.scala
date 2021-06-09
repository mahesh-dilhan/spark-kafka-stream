package io.mahesh

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkKafkaStream {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkKafkaStreaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(15))
    val sc = ssc.sparkContext
    sc.setLogLevel("ERROR")

    val kafkaConf = Map(
      "bootstrap.servers" -> "localhost:9092", // Default kafka broker list location
      "zookeeper.connect" -> "localhost:2181", // Default zookeeper location
      "group.id" -> "kafka-spark-streaming-example",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "zookeeper.connection.timeout.ms" -> "1000")

    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set("country"), kafkaConf))

    val lines = stream.map(record => (record.key, record.value))
    lines.print()

    ssc.start()
    ssc.awaitTermination()
  }

}