import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {

    public static void main(String[] args){
        Properties properties = new Properties();
        //kafka bootstrap server
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        properties.setProperty("key.deserializer",StringDeserializer.class.getName());
        properties.setProperty("value.serializer",StringDeserializer.class.getName());
        properties.setProperty("groupid","test");
        properties.setProperty("enable.auto.commit","true");
        properties.setProperty("auto.commit.intervals.ms","1000");
        properties.setProperty("auto.offset.reset","earliest");
        KafkaConsumer<String, String> kafkaConsumer =  new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("second_topic"));

        while(true){
            ConsumerRecords<String,String> consumerRecords  = kafkaConsumer.poll(100);
            for (ConsumerRecord<String,String>consumerRecord: consumerRecords){
                consumerRecord.value();
                consumerRecord.key();
                consumerRecord.offset();
                consumerRecord.partition();
                consumerRecord.timestamp();
                consumerRecord.topic();
            }

        }
    }

}
