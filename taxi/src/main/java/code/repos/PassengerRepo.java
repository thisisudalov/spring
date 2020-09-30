package code.repos;

import code.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {
    Passenger findById(long id);
}
