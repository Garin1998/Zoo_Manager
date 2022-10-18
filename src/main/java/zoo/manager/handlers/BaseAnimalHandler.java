package zoo.manager.handlers;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import zoo.manager.entities.Animal;
import zoo.manager.entities.Species;
import zoo.manager.entities.Zone;
import zoo.manager.models.requests.AddAnimalReq;
import zoo.manager.repositories.AnimalRepository;

@Service
public class BaseAnimalHandler implements AnimalRepositoryHandler {

    public final AnimalRepository animalRepository;
    public final SpeciesRepositoryHandler speciesRepositoryHandler;
    public final ZoneRepositoryHandler zoneRepositoryHandler;

    public BaseAnimalHandler(AnimalRepository animalRepository,
                             SpeciesRepositoryHandler speciesRepositoryHandler,
                             ZoneRepositoryHandler zoneRepositoryHandler) {
        this.animalRepository = animalRepository;
        this.speciesRepositoryHandler = speciesRepositoryHandler;
        this.zoneRepositoryHandler = zoneRepositoryHandler;
    }

    @Override
    public Iterable<Animal> findAllByZoneName(String zoneName) {
        Zone zone = checkIfZoneExistInDB(zoneName);
        return animalRepository.findAllByZoneUuid(zone.getUuid());
    }

    @Override
    public Iterable<Animal> findAllAnimalsByName(String animalName) {
        return animalRepository.findAllByName(animalName);
    }

    @Override
    public Animal addAnimal(AddAnimalReq animalReq) {
        Species species = checkIfSpeciesExistInDB(animalReq.getSpeciesName());
        Zone zone = checkIfZoneExistInDB(animalReq.getZoneName());
        Animal animal = new Animal(animalReq.getAnimalName(), species, zone);
        return animalRepository.save(animal);
    }

    Species checkIfSpeciesExistInDB(String name) throws NoSuchElementException {
        return speciesRepositoryHandler.findSpeciesByName(name)
                                       .orElseThrow(() -> new NoSuchElementException("Species not found"));
    }

    Zone checkIfZoneExistInDB(String name) throws NoSuchElementException {
        return zoneRepositoryHandler.findZoneByName(name)
                                    .orElseThrow(() -> new NoSuchElementException("Zone not found"));
    }
}
