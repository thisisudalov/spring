package code.repos;

import code.entities.ClassOfAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClassOfAutoRepo extends JpaRepository<ClassOfAuto, Long> {
}
