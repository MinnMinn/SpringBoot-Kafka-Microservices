package tea.io.account;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    NewTopic notification() {
        //topic name, partition numbers, replication number
        return new NewTopic("notification", 2, (short) 1);
    }

    @Bean
    NewTopic statistic() {
        //topic name, partition numbers, replication number
        return new NewTopic("statistic", 1, (short) 1);
    }
}
