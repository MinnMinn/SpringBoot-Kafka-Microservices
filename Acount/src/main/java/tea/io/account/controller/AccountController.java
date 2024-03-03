package tea.io.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tea.io.account.model.AccountDTO;
import tea.io.account.model.MessageDTO;
import tea.io.account.model.StatisticDTO;

import java.util.Date;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping(value = "/new")
    public AccountDTO create(@RequestBody AccountDTO account) {
        StatisticDTO stat = new StatisticDTO("Account " + account.getEmail() + " is created", new Date());

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(account.getEmail());
        messageDTO.setToName(account.getName());
        messageDTO.setSubject("Welcome Tea");
        messageDTO.setContent("Start with kafka");

        kafkaTemplate.send("notification", messageDTO).addCallback(new KafkaSendCallback<String, Object>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {

            }

            @Override
            public void onFailure(KafkaProducerException e) {

            }
        });
        kafkaTemplate.send("statistic", stat);

        return account;
    }
}
