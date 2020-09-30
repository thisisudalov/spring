package code.repos;

import code.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
