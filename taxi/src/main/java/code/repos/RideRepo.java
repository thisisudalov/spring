package code.repos;

import code.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RideRepo extends JpaRepository<Ride, Long> {

}
