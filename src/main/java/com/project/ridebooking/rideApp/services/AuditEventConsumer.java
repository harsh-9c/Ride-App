package com.project.ridebooking.rideApp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ridebooking.rideApp.configs.KafkaAuditConfig;
import com.project.ridebooking.rideApp.entities.AuditEvent;
import com.project.ridebooking.rideApp.entities.AuditLog;
import com.project.ridebooking.rideApp.repositories.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class AuditEventConsumer {

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditEventConsumer(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @KafkaListener(topics = KafkaAuditConfig.AUDIT_TOPIC,
            groupId = KafkaAuditConfig.AUDIT_GROUP,
            containerFactory = "auditKafkaListenerContainerFactory")
    public void handleAuditEvent( AuditEvent event, Acknowledgment acknowledgment) {
        try {
//            log.info("Received audit event: {} for entity: {}", event.getAction(), event.getEntityType());

            // Save audit event to database for long-term storage
            AuditLog auditLog = AuditLog.builder()
                    .auditId(event.getAuditId())
                    .entityType(event.getEntityType())
                    .action(event.getAction())
                    .userId(event.getUserId())
                    .userRole(event.getUserRole())
                    .previousValue(convertToJson(event.getPreviousValue()))
                    .newValue(convertToJson(event.getNewValue()))
                    .description(event.getDescription())
                    .timestamp(event.getTimestamp())
                    .status(event.getStatus())
                    .errorMessage(event.getErrorMessage())
                    .build();

            auditLogRepository.save(auditLog);

//            log.info("Successfully processed audit event: {}", event.getAuditId());

            // Manual acknowledgment
            acknowledgment.acknowledge();
        } catch (Exception e) {
//            log.error("Error processing audit event: {}", event.getAuditId(), e);
        }
    }

    private String convertToJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
//            log.error("Error converting object to JSON", e);
            return null;
        }
    }
}
