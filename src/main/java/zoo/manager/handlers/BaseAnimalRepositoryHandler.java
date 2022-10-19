package zoo.manager.handlers;

import java.util.NoSuchElementException;
import java.util.StringJoiner;
import org.springframework.stereotype.Service;
import zoo.manager.entities.Animal;
import zoo.manager.entities.Species;
import zoo.manager.entities.Zone;
import zoo.manager.models.requests.AddAnimalReq;
import zoo.manager.repositories.AnimalRepository;

/**
 * Class, which is used for implementation of {@link AnimalRepositoryHandler interface} and handle requests for animal.
 *
 * @author Krzysztof Kubiś
 * @version 1.0
 * @since JDK 17
 */
@Service
public class BaseAnimalRepositoryHandler implements AnimalRepositoryHandler {

    public final AnimalRepository animalRepository;
    public final SpeciesRepositoryHandler speciesRepositoryHandler;
    public final ZoneRepositoryHandler zoneRepositoryHandler;

    public BaseAnimalRepositoryHandler(AnimalRepository animalRepository,
                                       SpeciesRepositoryHandler speciesRepositoryHandler,
                                       ZoneRepositoryHandler zoneRepositoryHandler) {
        this.animalRepository = animalRepository;
        this.speciesRepositoryHandler = speciesRepositoryHandler;
        this.zoneRepositoryHandler = zoneRepositoryHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Animal> findAllByZoneName(String zoneName) {
        Zone zone = checkIfZoneExistInDB(zoneName);
        return animalRepository.findAllByZoneUuid(zone.getUuid());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Animal> findAllAnimalsByName(String animalName) {
        return animalRepository.findAllByName(animalName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addAnimal(AddAnimalReq animalReq)
        throws NoSuchElementException {
        Species species = checkIfSpeciesExistInDB(animalReq.getSpeciesName());
        Zone zone = checkIfZoneExistInDB(animalReq.getZoneName());
        Animal animal = new Animal(animalReq.getAnimalName(), species, zone);
        animalRepository.save(animal);
        StringJoiner sj = new StringJoiner(" ");
        return sj.add("Species").add(species.getUuid().toString()).add("has been added successfully").toString();
    }

    /**
     * Check if species with provided name already exist in database.
     *
     * @param name Name of provided species.
     *
     * @return Object of {@link Species Class} or throw {@link NoSuchElementException exception} if it doesn't exist.
     *
     * @throws NoSuchElementException species with provided name doesn't exist.
     */
    Species checkIfSpeciesExistInDB(String name)
        throws NoSuchElementException {
        return speciesRepositoryHandler.findSpeciesByName(name)
                                       .orElseThrow(() -> new NoSuchElementException("Species not found"));
    }

    /**
     * Check if zone with provided name already exist in database.
     *
     * @param name Name of provided zone.
     *
     * @return Object of {@link Zone Class} or throw {@link NoSuchElementException exception} if it doesn't exist.
     *
     * @throws NoSuchElementException zone with provided name doesn't exist.
     */
    Zone checkIfZoneExistInDB(String name)
        throws NoSuchElementException {
        return zoneRepositoryHandler.findZoneByName(name).orElseThrow(() -> new NoSuchElementException("Zone not found"));
    }
}
