package com.example.chapter4;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;

import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class FirstAppProducer {
    
    private static String topicName = "first-app";

    public static void main(String[] args) {
        // 1. KafkaProducerに必要な設定
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // 2. KafkaクラスタにMessageを送信（produce）するオブジェクトを生成
        Producer<Integer, String> producer = new KafkaProducer<>(properties);

        int key;
        String value;
        for (int i = 1; i <= 100; i++) {
            key = i;
            value = String.valueOf(i);

            // 3. 送信するMessageを生成
            ProducerRecord<Integer, String> record = new ProducerRecord<Integer,String>(topicName, key, value);

            // 4. Messageを送信し、Ackを受け取ったときに行う処理（Callback）を登録する
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (metadata != null) {
                        // 送信に成功した場合の処理
                        String infoString = String.format("Success partition:%d, offset:%d", metadata.partition(), metadata.offset());
                        System.out.println(infoString);
                    } else {
                        // 送信んに失敗した場合の処理
                        String infoString = String.format("Failed:%s", e.getMessage());
                        System.err.println(infoString);
                    }
                }
            });
        }
        // 5. KafkaProducerをクローズして終了
        producer.close();
    }
}