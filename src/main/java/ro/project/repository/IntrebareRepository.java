package ro.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.project.entity.Intrebare;

public interface IntrebareRepository extends JpaRepository<Intrebare, Integer> {
    Intrebare findById(int intrebareId);
}
