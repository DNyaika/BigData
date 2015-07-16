Web crawling with MapReduce
============

Introduction
-------
Web crawling using MapReduce. [Crawler4j API] (https://github.com/yasserg/crawler4j) is used to implement the crawler.
Crawled data in form of HTML pages is saved in MongoDB.

Installation
-------
Requirements: *JDK 7*, *Maven*, *Spark*

####With Maven

```
# Create a root folder, navigate to that folder and perform the following
mkdir -p src/main/java

copy > src/main/java/com/unitn/webcrawler/SparkImpl.java 
copy > src/main/java/com/unitn/webcrawler/WebCrawlerImpl.java
copy > src/main/java/com/unitn/webcrawler/CrawlControllerImpl.java 

copy > pom.xml

# Copy a file to use for data
copy > seedurls

# build the JAR
mvn clean package

# run the JAR
mvn exec:java -Dexec.mainClass="SparkImpl.java"
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
####Mongodb installation on Ubuntu

```
1: Import the public key used by the package management system
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10

2:Create the /etc/apt/sources.list.d/mongodb-org-3.0.list list file using the following command:
echo "deb http://repo.mongodb.org/apt/ubuntu "$(lsb_release -sc)"/mongodb-org/3.0

3:Reload local package database.
Issue the following command to reload the local package database:
sudo apt-get update

4:Install the latest stable version of MongoDB.
Issue the following command:

sudo apt-get install -y mongodb-org

5:Install MongoDB 3.0.4
sudo apt-get install -y mongodb-org=3.0.4 mongodb-org-server=3.0.4 mongodb-org-shell=3.0.4 mongodb-org-mongos=3.0.4 mongodb-org-tools=3.0.4

Pin a specific version of MongoDB.
Although you can specify any available version of MongoDB, apt-get will upgrade the packages when a newer version becomes available. To prevent unintended upgrades, pin the package. To pin the version of MongoDB at the currently installed version, issue the following command sequence:

echo "mongodb-org hold" | sudo dpkg --set-selections
echo "mongodb-org-server hold" | sudo dpkg --set-selections
echo "mongodb-org-shell hold" | sudo dpkg --set-selections
echo "mongodb-org-mongos hold" | sudo dpkg --set-selections
echo "mongodb-org-tools hold" | sudo dpkg --set-selections

------------------Run MongoDB-------------------
1: Start MongoDB.
Issue the following command to start mongod:

sudo service mongod start

2: Verify that MongoDB has started successfully
Verify that the mongod process has started successfully by checking the contents of the log file
at /var/log/mongodb/mongod.log for a line reading

[initandlisten] waiting for connections on port <port>
where <port> is the port configured in /etc/mongod.conf, 27017 by default.

3:Stop MongoDB.
As needed, you can stop the mongod process by issuing the following command:
sudo service mongod stop

4:Restart MongoDB.
Issue the following command to restart mongod:

sudo service mongod restart
```
Report
-------
[BigData Project (PDF)](/documentation/Web_Crawling_with_MapReduce1 .pdf)
