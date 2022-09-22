package zoo.manager.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import zoo.manager.entities.Animal;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {

    Iterable<Animal> findAllByZoneUuid(UUID zoneUUID);
    Iterable<Animal> findAllByName(String animalName);
}
