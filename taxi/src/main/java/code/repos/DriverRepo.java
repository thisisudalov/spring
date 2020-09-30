package code.repos;

import code.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DriverRepo extends JpaRepository<Driver, Long> {
    List<Driver> findByIsFreeTrue();

    @Query(
            value = "select * from driver where free=1 and stage>= :stage",
    nativeQuery = true)
    List<Driver> findFreeDriverByStage(Long stage);
}
