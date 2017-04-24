# UnauthorizedLogAnalysis

After cloning the project run:

1. sbt package
2. <YOUR_SPARK_HOME>/bin/spark-submit --class "main.scala.LogAnalysis" --master local target/scala-2.11/unauthorized-log-analysis_2.11-1.0.jar
