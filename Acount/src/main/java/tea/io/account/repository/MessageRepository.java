package tea.io.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tea.io.account.model.MessageDTO;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageDTO, Integer> {
    List<MessageDTO> findByStatus(boolean status);
}
