package tea.io.account.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class MessageDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String to;
    private String toName;
    private String subject;
    private String content;
    private boolean status;

    public MessageDTO() {
    }

    public MessageDTO(int id, String to, String toName, String subject, String content, boolean status) {
        this.id = id;
        this.to = to;
        this.toName = toName;
        this.subject = subject;
        this.content = content;
        this.status = status;
    }
}
