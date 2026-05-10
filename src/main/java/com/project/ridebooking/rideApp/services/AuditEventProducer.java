package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.configs.KafkaAuditConfig;
import com.project.ridebooking.rideApp.entities.AuditEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuditEventProducer {

    private final KafkaTemplate<String, AuditEvent> kafkaTemplate;

    @Autowired
    public AuditEventProducer(KafkaTemplate<String, AuditEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishAuditEvent(AuditEvent event) {
        try {
//            log.info("Publishing audit event: {} for entity: {} with id: {}",
//                    event.getAction(), event.getEntityType(), event.getEntityId());

            kafkaTemplate.send(KafkaAuditConfig.AUDIT_TOPIC, event.getAuditId(), event).get();
            System.out.println("🔥 PRODUCER METHOD CALLED");
//                    .whenComplete((result, exception) -> {
//                        if (exception == null) {
//                            log.info("Successfully published audit event: {} with offset: {}",
//                                    event.getAuditId(), result.getRecordMetadata().offset());
//                        } else {
//                            log.error("Failed to publish audit event: {}", event.getAuditId(), exception);
//                        }
//                    });
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("Error publishing audit event", e);
        }
    }

}
