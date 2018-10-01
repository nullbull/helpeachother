package com.heo.common.Serializer;

import java.util.Properties;

/**
 * @Auth justinniu
 * @Date 2018/9/4
 * @Desc
 */
public class PropertiesUtil {
    private PropertiesUtil() {}
    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "59.110.137.45:9092");
        properties.put("acks", "all");
        properties.put("batch.size", 16384);
        properties.put("retries", 0);
        properties.put("metadata.fetch.timeout.ms", 30000);
        //Reduce the no of requests less than 0
        properties.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }


}
