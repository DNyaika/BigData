package com.unitn.webcrawler;

import scala.Tuple2;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SparkImpl {

    private static final Logger logger = LoggerFactory.getLogger(SparkImpl.class);
    private static final Pattern COMMA = Pattern.compile(",");
    static int counter = 0;

    public static void main(String[] args) throws Exception {

        SparkConf sparkConf = new SparkConf().setAppName("WebCrawler").setMaster("local");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = ctx.textFile("seedurls", 1);

        JavaRDD<String> urls = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String s) {
                return Arrays.asList(COMMA.split(s));
            }
        });

        JavaPairRDD<String, String> map = urls.flatMapToPair(new PairFlatMapFunction<String, String, String>() {
            @Override
            public Iterable<Tuple2<String, String>> call(String s) throws Exception {
                Set<Tuple2<String, String>> crawledData = new HashSet<>();
                CrawlControllerImpl crawlController = new CrawlControllerImpl(s);
                crawlController.executeController();
                Map<String, String> map = CrawledDataSource.crawledData;
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    String url = pair.getKey().toString();
                    String html = pair.getValue().toString();
                    crawledData.add(new Tuple2<>(url, html));
                    it.remove(); // avoids a ConcurrentModificationException
                }
                return crawledData;
            }
        });

        JavaPairRDD<String, String>  reducer = map.reduceByKey(new Function2<String, String, String>() {
            @Override
            public String call(String string1, String string2) {

                try {
                    //Mongo DB Client
                    MongoClient mongo = new MongoClient();
                    StringBuilder sb = new StringBuilder();
                    Random random = new Random();
                   logger.info("Reaching here");
                    sb.append(string1);
                    sb.append(string2);
                    int n = random.nextInt();
                    logger.info("Random Number = " + n);
                   //  Saving html and url as key to mongoDB
                    MongoDatabase db = mongo.getDatabase("bigDCourse");
                    db.getCollection("webpages").insertOne(
                            new Document("webpage",
                                    new Document()
                                    .append(Integer.toString(n), sb)));

                } catch (MongoException e) {

                } 
                return string2;
            }
        });

        List<Tuple2<String, String>> output = reducer.collect();
        for (Tuple2<?, ?> tuple : output) {
            logger.info("key:::" + tuple._1() + ": " + "Value::::" + tuple._2());
        }
        ctx.stop();
    }
}
