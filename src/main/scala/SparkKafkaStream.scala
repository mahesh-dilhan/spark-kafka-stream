package io.mahesh


import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkKafkaStream {
  def main(args: Array[String]): Unit = {
    //print("hello")

    val conf = new SparkConf().setAppName("SparkKafkaStreaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(15))
    val sc = ssc.sparkContext
    sc.setLogLevel("ERROR")

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("country")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](topics, kafkaParams)
    )

   val lines = stream.map(record => (record.key, record.value))
    lines.print()
  }
}
