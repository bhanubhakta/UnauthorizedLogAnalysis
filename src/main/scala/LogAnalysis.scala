

package main.scala
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object LogAnalysis {
  def main(args: Array[String]) {
    //Read log file.
    val conf = new SparkConf().setAppName("Log Analysis")
    val sc = new SparkContext(conf)
    val myLogs = sc.textFile("/Users/bhanubhaktasigdel/Desktop/Big-Data/Apache-Spark/apache-samples/access_log.txt")
    val modifiedLogs = myLogs.map(line => line.replace("\"", "'"))
    val modifiedTuples = modifiedLogs.map(line => (line.split(" ")(0), if (line.split(" ")(8).toInt == 401) 1 else 0))
    val groupedByKeys = modifiedTuples.groupByKey().sortByKey()
    val avgs = groupedByKeys.map(pair => (pair._1, average(pair._2)))
    avgs.saveAsTextFile("/Users/bhanubhaktasigdel/Documents/workspace/UnauthorizedLogAnalysis/user_anauthorized_frequency")
  }

  def average[T](ts: Iterable[T])(implicit num: Numeric[T]) = {
    num.toDouble(ts.sum) / ts.size
  }
}