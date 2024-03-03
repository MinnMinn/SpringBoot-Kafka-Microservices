package nhim.io.statistic.service;

import nhim.io.statistic.entity.Statistic;
import nhim.io.statistic.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class StatisticService {
    private final Logger logger = Logger.getLogger(String.valueOf(StatisticService.class));

    @Autowired
    private StatisticRepository statisticRepository;

    @KafkaListener(id = "statisticGroup", topics = "statistic")
    public void listen(Statistic statistic) {
        logger.info("Received: " + statistic.getMessage());
        statisticRepository.save(statistic);
    }
}
