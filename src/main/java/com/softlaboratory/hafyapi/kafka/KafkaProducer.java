package com.softlaboratory.hafyapi.kafka;

import com.softlaboratory.hafyapi.constant.AppConstant;
import com.softlaboratory.hafyapi.service.TypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Log4j2
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, ResponseEntity<Object>> kafkaTemplate;

    @Autowired
    private TypeService service;

    public ListenableFuture<SendResult<String, ResponseEntity<Object>>> sendMessage() {
        log.info("Send message -> Get All");
        return kafkaTemplate.send(AppConstant.KAFKA_TOPIC_NAME, service.getAll());
    }

}
