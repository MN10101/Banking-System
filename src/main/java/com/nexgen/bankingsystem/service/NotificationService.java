package com.nexgen.bankingsystem.service;

import com.nexgen.bankingsystem.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private EmailUtil emailUtil;

    public void sendIPNotification(String email, String ip) {
        if (email == null || email.isEmpty() || ip == null || ip.isEmpty()) {
            logger.warn("Invalid email or IP address provided for IP notification.");
            return;
        }

        String subject = "New Login Detected";
        String text = "A new login from IP address: " + ip + " was detected on your account.";
        sendNotification(email, subject, text);
    }

    public void sendNotification(String email, String subject, String text) {
        if (email == null || email.isEmpty() || subject == null || subject.isEmpty() || text == null || text.isEmpty()) {
            logger.warn("Invalid notification details. Email, subject, or text is missing.");
            return;
        }

        try {
            emailUtil.sendSimpleMessage(email, subject, text);
            logger.info("Notification sent successfully to {}", email);
        } catch (Exception e) {
            logger.error("Failed to send notification to {}: {}", email, e.getMessage());
        }
    }
}
