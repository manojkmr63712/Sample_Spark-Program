import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils
object StreamExam {
  def main(args: Array[String]): Unit = {
    System.setProperty("twitter4j.oauth.consumerKey", "2EieO6EkUVYiibLa13dn1kAav")
    System.setProperty("twitter4j.oauth.consumerSecret", "qMOdgm86dneG26kesStEYp0eTNzqyz3T9yVWmBMsV1fyItYD8l")
    System.setProperty("twitter4j.oauth.accessToken", "872408787117686784-MRLmo6f6xX4CfOuk4vdsKWiXramJiLb")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "hdlUdz5DUCahHepm9M8neB1kyfWbgREWRJVlONAB8isR9")
    val conf = new SparkConf().setAppName("Stream Data")
      conf.setMaster("local[*]")
    val filters = Seq("india")
    val ssc = new StreamingContext(conf, Seconds(20))
    val streamtweet = TwitterUtils.createStream(ssc, None, filters)
    streamtweet.print()
    ssc.start()
    ssc.awaitTermination()
  }

}
