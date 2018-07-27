name := "CDH"

version := "1.0"

scalaVersion := "2.12.2"

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.38"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.1.1"

// https://mvnrepository.com/artifact/org.apache.spark/spark-streaming_2.11
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.1.1"

// https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-twitter_2.11
libraryDependencies += "org.apache.spark" % "spark-streaming-twitter_2.11" % "1.6.0"