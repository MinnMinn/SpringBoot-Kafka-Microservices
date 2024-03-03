package minn.io.notification.service;

import minn.io.notification.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

public interface EmailService {
    void sendEmail(MessageDTO messageDTO);
}

@Service
class EmailServiceImpl implements EmailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username")
    private String from;

    @Override
    @Async
    public void sendEmail(MessageDTO messageDTO) {
        try {
            logger.info("Sending email");

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            helper.setTo(messageDTO.getTo());
            helper.setText("Yeah Yeah!");
            helper.setSubject(messageDTO.getSubject());
            helper.setFrom("nguyenminhtung.fpt@gmail.com");
            javaMailSender.send(message);

            logger.info("END ... Email sent successfully");
        } catch (MessagingException e) {
            logger.error("Email sent with error: " + e.getMessage());
        }
    }
}
