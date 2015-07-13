Web crawling with MapReduce
============

Introduction
-------

Web crawling using MapReduce (Apache Spark, Java7,Crawler4j API https://github.com/yasserg/crawler4j)
Crawled data in form of HTML pages is saved in mongo DB

Installation
-------
Requirements: *JDK 7*, *Maven*, *Spark*

####Run with IDE

Add the libraries from **sequential-libs** folder to your classpath.

Clean-install application with Maven
> mvn clean install

Start the master node using the following command
> spark-class org.apache.spark.deploy.master.Master

Start the slave nodes using the following command
> spark-class org.apache.spark.deploy.worker.Worker spark://{masterIp}:7077

Run the main method of a **it.unitn.bd.bfs.BfsSpark** class

####Run with Spark

Clean-install application with Maven
> mvn clean install

Start the master node using the following command
> spark-class org.apache.spark.deploy.master.Master

Start the slave nodes using the following command
> spark-class org.apache.spark.deploy.worker.Worker spark://{masterIp}:7077

Submit Spark job from the JAR folder
> spark-submit BFS-wit-hMapReduce-${version}-jar-with-dependencies.jar

Documentation
-------
[BigData Project (PDF)](/docs/BigData_Project.pdf)
