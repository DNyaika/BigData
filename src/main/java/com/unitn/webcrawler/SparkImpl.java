package com.unitn.webcrawler;

import com.mongodb.MongoClient;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.util.*;
import java.util.regex.Pattern;

public final class SparkImpl {

    private static final Logger logger = LoggerFactory.getLogger(SparkImpl.class);
    private static final Pattern COMMA = Pattern.compile(",");

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
            public Iterable<Tuple2<String, String>> call(String string) throws Exception {
                CrawlControllerImpl.executeController(string);
                Set<Tuple2<String, String>> crawledData = new HashSet<>(CrawlControllerImpl.crawledData.size());

                for (Map.Entry<String, String> pair : CrawlControllerImpl.crawledData.entrySet()) {
                    crawledData.add(new Tuple2<>(pair.getKey(), pair.getValue()));
                }
                logger.info("map:::" + crawledData);

                return crawledData;
            }
        });

        JavaPairRDD<String, String> reducer = map.reduceByKey(new Function2<String, String, String>() {
            @Override
            public String call(String string1, String string2) {
                // Saving html and url as key to MongoDB
                new MongoClient().getDatabase("bigDCourse").getCollection("webpages").insertOne(
                        new Document("webpage",
                                new Document().append(
                                        String.valueOf(new Random().nextInt()), string1 + string2)));
                return string2;
            }
        });

        for (Tuple2<?, ?> tuple : reducer.collect()) {
            logger.info("key:::" + tuple._1() + ": " + "value:::" + tuple._2());
        }

        ctx.stop();
    }
}
