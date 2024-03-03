package tea.io.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tea.io.account.model.MessageDTO;
import tea.io.account.repository.MessageRepository;
import tea.io.account.repository.StatisticRepository;

import java.util.List;

@Service
public class PollingService {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    StatisticRepository statisticRepository;

    @Scheduled(fixedDelay = 1000)
    public void producer() {
        List<MessageDTO> messageDTOS = messageRepository.findByStatus(false);

        for (MessageDTO messageDTO : messageDTOS) {
            kafkaTemplate.send("notification", messageDTO).addCallback(new KafkaSendCallback<String, Object>() {

                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    messageDTO.setStatus(true);
                    messageRepository.save(messageDTO);

                }

                @Override
                public void onFailure(KafkaProducerException e) {

                    e.printStackTrace();
                }
            });
        }
    }
}
