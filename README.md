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

```xml
 Create a root folder,navigate to that folder and perform the following

mkdir -p src/main/java

cat > src/main/java/WebCrawler.java 

# project model (cut&paste)!
cat > pom.xml!

# copy a file to use for data!
cp sample

# build the JAR!
mvn clean package!

# run the JAR!
mvn exec:java -Dexec.mainClass="webcrawler"
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

