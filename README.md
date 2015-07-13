Web crawling with MapReduce
============

Introduction
-------
Web crawling using MapReduce. Crawler4j API https://github.com/yasserg/crawler4j is used to implement the crawler

Crawled data in form of HTML pages is saved in mongodb

Installation
-------
Requirements: *JDK 7*, *Maven*, *Spark*

####With Maven

```
Create a root folder,navigate to that folder and perform the following

mkdir -p src/main/java

copy > src/main/java/com/unitn/webcrawler/BasicCrawler.java 
copy > src/main/java/com/unitn/webcrawler/CrawlControllerImpl.java 
copy > src/main/java/com/unitn/webcrawler/CrawledDataSource.java 
copy > src/main/java/com/unitn/webcrawler/WebCrawler.java

copy > pom.xml

Copy a file to use for data
cp seedurls

# build the JAR
mvn clean package

# run the JAR
mvn exec:java -Dexec.mainClass="WebCrawler.java"
```

####Starting a Cluster Manually
```
You can start a standalone master server by executing:

./sbin/start-master.sh

Once started, the master will print out a spark://HOST:PORT URL for itself, which you can use to connect workers to it, or pass as the “master” argument to SparkContext. You can also find this URL on the master’s web UI, which is http://localhost:8080 by default.
Similarly, you can start one or more workers and connect them to the master via:

./bin/spark-class org.apache.spark.deploy.worker.Worker spark://IP:PORT

Submiting a spark Job
bin/spark-submit foldercontainingjar/target/filename.jar
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

