package org.example.bloodwave.application.service.impl;

import lombok.AllArgsConstructor;
import org.example.bloodwave.application.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    public final JavaMailSender javaMailSender;

    public void sendEmail(String to,String subject,String body)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(body);

                javaMailSender.send(simpleMailMessage);
    }

    public void sendResetPasswordEmail(String email, String token) {
            String resetLink =
                    "http://localhost:4200/reset-password?token=" + token;

            String content = """
        You requested a password reset.
        
        Click the link below:
        %s
        
        This link expires in 15 minutes.
        """.formatted(resetLink);

            this.sendEmail(email, "Reset your password", content);
        }
    }
