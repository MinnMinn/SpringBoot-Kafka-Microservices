package tea.io.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tea.io.account.model.StatisticDTO;

import java.util.List;

public interface StatisticRepository extends JpaRepository<StatisticDTO, Integer> {
    List<StatisticDTO> findByStatus(boolean status);
}
