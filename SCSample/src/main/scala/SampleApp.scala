import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
 
object SampleApp {
  def main(args: Array[String]) {
    val txtFile = "/home/manoj/workspace/SCSample/src/main/scala/SampleApp.scala"
    val conf = new SparkConf().setAppName("Sample Application")
    val sc = new SparkContext(conf)
    val txtFileLines = sc.textFile(txtFile , 2).cache()
    val numAs = txtFileLines .filter(line => line.contains("val")).count()
    println("Lines with val: %s".format(numAs))
  }
}
