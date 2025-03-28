package com.Service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // Enable HTML content
            helper.setFrom("your-email@gmail.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendEmailWithAttachment(String to, String subject, String body, MultipartFile file)
            throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.setFrom("your-email@gmail.com");

        if (!file.isEmpty()) {
            // Tạo tệp tạm thời an toàn hơn
            File tempFile = Files.createTempFile("upload-", file.getOriginalFilename()).toFile();
            Files.copy(file.getInputStream(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            helper.addAttachment(file.getOriginalFilename(), tempFile);

            // Xóa tệp sau khi gửi
            tempFile.deleteOnExit();
        }

        mailSender.send(message);
    }
}
