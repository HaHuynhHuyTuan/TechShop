package com.Controller;

import com.Service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller // Sửa @RestController -> @Controller để trả về trang HTML
@RequestMapping("/admin/mail")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/form") // Hiển thị trang mailForm.html
    public String showMailForm(Model model) {
        return "mailForm"; // Tên file trong thư mục templates (mailForm.html)
    }

    @PostMapping("/send") // API gửi email
    @ResponseBody
    public String sendMail(@RequestParam String to, 
                           @RequestParam String subject,
                           @RequestParam String body,
                           @RequestParam(required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                mailService.sendEmailWithAttachment(to, subject, body, file);
                return "Email with attachment sent successfully!";
            } else {
                mailService.sendEmail(to, subject, body);
                return "Email sent successfully!";
            }
        } catch (MessagingException | IOException e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
