package com.prem.SnakeAndLadderAPi.Service;

import com.prem.SnakeAndLadderAPi.Pojo.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

@Service
public class SendAndResponseService {
    static final String TOPIC = "quickstart-events";
    @Autowired
    KafkaTemplate<Object, Game> kafkaTemplate;


    public void sendMessage(Game msg) {
        System.out.println(kafkaTemplate);
        if (kafkaTemplate != null) {
            kafkaTemplate.send(TOPIC, msg);
        }
    }
}
