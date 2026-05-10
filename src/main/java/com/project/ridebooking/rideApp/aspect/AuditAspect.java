package com.project.ridebooking.rideApp.aspect;

import com.project.ridebooking.rideApp.annotation.Auditable;
import com.project.ridebooking.rideApp.entities.AuditEvent;
import com.project.ridebooking.rideApp.entities.User;
import com.project.ridebooking.rideApp.security.JWTService;
import com.project.ridebooking.rideApp.services.AuditEventProducer;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Aspect
public class AuditAspect {
    private final AuditEventProducer auditEventProducer;
    private final HttpServletRequest request;
    private final JWTService jwtService;

    @Autowired
    public AuditAspect(AuditEventProducer auditEventProducer, HttpServletRequest request, JWTService jwtService) {
        this.auditEventProducer = auditEventProducer;
        this.request = request;
        this.jwtService = jwtService;
    }

    @Around("@annotation(auditable)")
    public Object auditMethod( ProceedingJoinPoint joinPoint, Auditable auditable) throws Throwable {
        Object result = null;
        String errorMessage = null;
        String status = "SUCCESS";

        try {
            result = joinPoint.proceed();
            return result;
        } catch (Exception e) {
            status = "FAILURE";
            errorMessage = e.getMessage();
            throw e;
        } finally {
            publishAuditEvent(joinPoint, auditable, result, errorMessage, status);
        }
    }

    private void publishAuditEvent(ProceedingJoinPoint joinPoint, Auditable auditable,
                                   Object result, String errorMessage, String status) {
        try {
            String userId = getCurrentUserId();
            String userEmail = getCurrentUserEmail();
            String userRole = getCurrentUserRole();

            AuditEvent auditEvent = AuditEvent.builder()
                    .auditId(UUID.randomUUID().toString())
                    .entityType(auditable.entityType())
                    .action(auditable.action())
                    .description(auditable.description())
                    .userId(userId)
                    .userRole(userRole)
                    .timestamp(LocalDateTime.now())
                    .status(status)
                    .errorMessage(errorMessage)
                    .newValue(auditable.captureResult() ? result : null)
                    .build();

            auditEventProducer.publishAuditEvent(auditEvent);
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("Error creating audit event", e);
        }
    }

    private String getCurrentUserId() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        if (securityContext != null && securityContext.getAuthentication() != null) {
//            return securityContext.getAuthentication().getName();
//        }
        return "ANONYMOUS";
    }

    private String getCurrentUserEmail() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        if(securityContext!=null && securityContext.getAuthentication()!=null){
//            Jwt jwt = (Jwt) securityContext.getAuthentication().getPrincipal();
//            return jwt.ge
//        }
        return "";
    }

    private String getCurrentUserRole() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        if (securityContext != null && securityContext.getAuthentication() != null) {
//            Collection<? extends GrantedAuthority> authorities =
//                    securityContext.getAuthentication().getAuthorities();
//            return authorities.stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.joining(","));
//        }
        return "UNKNOWN";
    }

}
