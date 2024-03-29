package minn.io.notification.service;

import lombok.AllArgsConstructor;
import minn.io.notification.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private EmailService emailService;

    @KafkaListener(id = "notificationGroup", topics = "notification")
    public void listen(MessageDTO messageDTO) {
        logger.info("Received: ", messageDTO.getTo());
        emailService.sendEmail(messageDTO);
    }
}
