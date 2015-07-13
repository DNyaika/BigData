Web crawling with MapReduce
============

Introduction
-------
Web crawling using MapReduce Crawler4j API https://github.com/yasserg/crawler4j is used to implement the crawler

Crawled data in form of HTML pages is saved in mongodb

Installation
-------
Requirements: *JDK 7*, *Maven*, *Spark*

####with Maven

```
Create a root folder,navigate to that folder and perform the following

mkdir -p src/main/java

copy > src/main/java/com/unitn/webcrawler/BasicCrawler.java 
copy > src/main/java/com/unitn/webcrawler/CrawlControllerImpl.java 
copy > src/main/java/com/unitn/webcrawler/CrawledDataSource.java 
copy > src/main/java/com/unitn/webcrawler/SparkCrawler.java 

copy > pom.xml

Copy a file to use for data
cp seedurls

# build the JAR
mvn clean package

# run the JAR
mvn exec:java -Dexec.mainClass="WebCrawler.java"
```

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

