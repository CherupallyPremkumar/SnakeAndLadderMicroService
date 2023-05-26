package com.prem.SnakeAndLadderAPi.Config;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.prem.SnakeAndLadderAPi.Pojo.Game;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KakfaProducer {
    @Bean
    public ProducerFactory<Object, Game> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<Object, Game> kafkaTemplate() {
       KafkaTemplate<Object,Game> d= new KafkaTemplate<>(producerFactory());
        System.out.println(d.getKafkaAdmin()+" "+d.getDefaultTopic());
       return d;
    }
}
