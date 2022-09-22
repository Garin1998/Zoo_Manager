package zoo.manager.handler;

import org.springframework.stereotype.Service;
import zoo.manager.entities.Animal;
import zoo.manager.entities.Species;
import zoo.manager.entities.Zone;
import zoo.manager.model.request.AnimalReq;
import zoo.manager.repository.AnimalRepository;

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
    public Iterable<Animal> findAllAnimals() {
        return animalRepository.findAll();
    }

    @Override
    public Iterable<Animal> findAllByZoneName(String zoneName) {
        Zone zone = checkIfZoneExistInDB(zoneName);
        if(zone == null) {
            return null;
        }
        return animalRepository.findAllByZoneUuid(zone.getUuid());
    }

    @Override
    public Iterable<Animal> findAllAnimalsByName(String animalName) {
        return animalRepository.findAllByName(animalName);
    }

    @Override
    public Animal addAnimal(AnimalReq animalReq) {
        Species species = checkIfSpeciesExistInDB(animalReq.getSpeciesName());
        Zone zone = checkIfZoneExistInDB(animalReq.getZoneName());
        if(species == null || zone == null) {
            return null;
        }
        Animal animal = new Animal(animalReq.getAnimalName(), species, zone);
        return animalRepository.save(animal);
    }

    Species checkIfSpeciesExistInDB(String name) {
        return speciesRepositoryHandler.findSpeciesByName(name).orElse(null);
    }

    Zone checkIfZoneExistInDB(String name) {
        return zoneRepositoryHandler.findZoneByName(name).orElse(null);
    }
}
