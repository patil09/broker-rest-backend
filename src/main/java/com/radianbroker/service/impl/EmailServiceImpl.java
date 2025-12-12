package com.radianbroker.service.impl;

import com.radianbroker.dto.Mail;
import com.radianbroker.exceptions.ResourceNotFoundException;
import com.radianbroker.repository.UserRepository;
import com.radianbroker.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender sonoconnectMailSender;

    @Value("${app.name}")
    private String appName;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String appSupportEmail;

    @Autowired
    private Configuration freemarkerConfig;

    public String getAppName() {
        return new String(appName + "-" + activeProfile).toUpperCase();
    }

    @Override
    public void sendFailedHL7MSG(String error, String to, String orderNo) throws Exception {
        String subject = "Order No " + orderNo + ": Report send failed";

        if (to == null) {
            throw new ResourceNotFoundException("User not found with email: " + to);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("radianAdminName", "there");
        model.put("appName", appName);
        model.put("error", error);

        Template t = freemarkerConfig.getTemplate("send-failed-HL7MSG.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        sendMail(appSupportEmail, to, subject, html);
    }



    private void sendMail(String from, String to, String subject, String html) throws MessagingException {
        MimeMessage mail = sonoconnectMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
        helper.setFrom(from);
        if (to.contains(",")) {
            helper.setTo(to.split(","));
        } else {
            helper.setTo(to);
        }
        helper.setSubject(subject);
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(html, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        mail.setContent(multipart);

        sonoconnectMailSender.send(mail);
        System.out.println("{} mail sent to: {}" + subject + " " + to);
    }


    @Override
    public void sendExceptionNotificationEmail(Mail mail) throws Exception {
        MimeMessage message = sonoconnectMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template t = freemarkerConfig.getTemplate(mail.getTemplate());

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(getAppName() + ":" + mail.getSubject());
            helper.setFrom(mail.getFrom());

            if (mail.getCc() != null && mail.getCc().length > 0) {
                helper.setCc(mail.getCc());
            }
            sonoconnectMailSender.send(message);
            System.out.println("Email Sent..");
        } catch (MessagingException | IOException | TemplateException e) {
            System.out.println("mail exception = " + e.getMessage());
            e.printStackTrace();
        }
    }

}
