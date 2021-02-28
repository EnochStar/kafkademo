import consumer.ConsumerCreator;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import producer.ProducerCreator;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

/**
 * @author EnochStar
 * @title: Test
 * @projectName kafakademo
 * @description: TODO
 * @date 2021/2/28 14:45
 */
public class Test {
    private static final String TOPIC = "test-topic";
    @org.junit.Test
    public void sendMessage() {

        Producer<String, String> producer = ProducerCreator.createProducer();
        ProducerRecord<String, String> record =
                new ProducerRecord<>(TOPIC, "hello, Kafka!");
        try {
            //send message
            RecordMetadata metadata = producer.send(record).get();
            System.out.println("Record sent to partition " + metadata.partition()
                    + " with offset " + metadata.offset());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Error in sending record");
            e.printStackTrace();
        }
        producer.close();
    }
    @org.junit.Test
    public void getMessage() {
        Consumer<String, String> consumer = ConsumerCreator.createConsumer();
// 循环消费消息
        while (true) {
            //subscribe topic and consume message
            consumer.subscribe(Collections.singletonList(TOPIC));

            ConsumerRecords<String, String> consumerRecords =
                    consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println("Consumer consume message:" + consumerRecord.value());
            }
        }
    }
}
