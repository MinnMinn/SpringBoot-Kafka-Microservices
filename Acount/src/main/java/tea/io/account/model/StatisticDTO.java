package tea.io.account.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class StatisticDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String message;
    private Date creationDate;
    private boolean status;

    public StatisticDTO() {
    }

    public StatisticDTO(String message, Date creationDate) {
        this.message = message;
        this.creationDate = creationDate;
    }
}
