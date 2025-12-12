package com.radianbroker.service;

import com.radianbroker.dto.Mail;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendFailedHL7MSG(String error, String string, String orderNo) throws Exception;

    void sendExceptionNotificationEmail(Mail mail) throws Exception;

}
