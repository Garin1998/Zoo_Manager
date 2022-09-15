package zoo.manager.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import zoo.manager.entities.Zone;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {

}
