package ro.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.project.entity.Rezultat;

import java.util.List;

public interface RezultatRepository extends JpaRepository<Rezultat, Integer> {
    List<Rezultat> findBySesiuneId(String sessionId);
}
