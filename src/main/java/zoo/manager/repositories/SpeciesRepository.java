package zoo.manager.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import zoo.manager.entities.Species;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {

    Optional<Species> findSpeciesByName(String name);

    boolean existsSpeciesByName(String name);

}
