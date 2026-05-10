package com.project.ridebooking.rideApp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditEvent {

    private String auditId;
    private String entityType;          // e.g., "USER", "RIDE", "DRIVER", "PAYMENT"
    private String action;              // e.g., "CREATE", "UPDATE", "DELETE", "LOGIN"
    private String userId;              // Who performed the action
    private String userRole;
    private Object previousValue;       // For updates
    private Object newValue;            // For updates
    private String description;
    private String userAgent;
    private LocalDateTime timestamp;
    private String status;              // SUCCESS, FAILURE
    private String errorMessage;

}
