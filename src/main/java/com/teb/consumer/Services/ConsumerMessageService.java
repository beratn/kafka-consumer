package com.teb.consumer.Services;

import com.teb.consumer.Interfaces.IKafkaConstants;
import com.teb.consumer.Database.MongoStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerMessageService {

    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = IKafkaConstants.TOPIC_NAME, groupId = IKafkaConstants.GROUP_ID_CONFIG)
    public void listen(String message) {
        MongoStarter mongo = new MongoStarter();
        mongo.save(message);
        template.convertAndSend("/topic/log", message);

    }
}
