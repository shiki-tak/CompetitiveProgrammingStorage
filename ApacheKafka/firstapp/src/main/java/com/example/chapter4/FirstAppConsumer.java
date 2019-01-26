package com.example.chapter4;

import java.util.*;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class FirstAppConsumer {

    private static String topicName = "first-app";

    public static void main(String[] args) {
        // 1.KafkaConsumerに必要な設定
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "FirstAppConsumerGroup");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // 2. KafkaクラスタからMessageを受信（Consume）するオブジェクトを作成
        Consumer<Integer, String> consumer = new KafkaConsumer<>(properties);

        // 3. 受信（subscribe）するTopicを登録
        List<String> topicList = new ArrayList<>(1);
        topicList.add(topicName);
        consumer.subscribe(topicList);

        for (int count = 0; count < 300; count++) {
            // 4. Messageを受信し、コンソールに表示する
            ConsumerRecords<Integer, String> records = consumer.poll(1);
            for (ConsumerRecord<Integer, String> record: records) {
                String msgString = String.format("key:%d, value:%s", record.key(), record.value());
                System.out.println(msgString);

                // 5. 処理が完了したMessageのOffsetをCommitする
                TopicPartition tp = new TopicPartition(record.topic(), record.partition());
                OffsetAndMetadata oam = new OffsetAndMetadata(record.offset() + 1);
                Map<TopicPartition, OffsetAndMetadata> commitInfo = Collections.singletonMap(tp, oam);
                consumer.commitSync(commitInfo);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 6. KafkaConsumerをクローズして終了
        consumer.close();
    }
}