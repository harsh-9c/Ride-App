package com.project.ridebooking.rideApp.repositories;

import com.project.ridebooking.rideApp.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUserId(String userId);
    List<AuditLog> findByTimestampBetween( LocalDateTime start, LocalDateTime end);
}