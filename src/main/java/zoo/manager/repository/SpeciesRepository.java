package zoo.manager.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import zoo.manager.entities.Species;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {

}
