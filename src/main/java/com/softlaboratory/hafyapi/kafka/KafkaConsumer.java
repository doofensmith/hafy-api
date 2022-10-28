package com.softlaboratory.hafyapi.kafka;

import com.softlaboratory.hafyapi.constant.AppConstant;
import com.softlaboratory.hafyapi.domain.dto.TypeDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class KafkaConsumer {

    @Autowired
    KafkaTemplate<String, ResponseEntity<Object>> template;

    @KafkaListener(topics = AppConstant.KAFKA_TOPIC_NAME, groupId = AppConstant.KAFKA_GROUP_ID_ACC_TYPE)
    public void consume(TypeDto response) {
        log.info("Message received -> {}", response);
    }

}
