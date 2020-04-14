package ro.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.project.entity.Raspuns;

import java.util.List;

public interface RaspunsRepository extends JpaRepository<Raspuns, Integer> {
    Raspuns findByIntrebareIdAndSesiuneId(int intrebareId, String sessionId);

    List<Raspuns> findBySesiuneId(String sessionId);
}
