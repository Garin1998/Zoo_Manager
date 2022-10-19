package zoo.manager.handlers;

import java.util.NoSuchElementException;
import zoo.manager.entities.Animal;
import zoo.manager.models.requests.AddAnimalReq;

/**
 * Interface which is used for using methods implemented in {@link BaseAnimalRepositoryHandler Class}
 *
 * @author Krzysztof Kubiś
 * @version 1.0
 * @since JDK 17
 */
public interface AnimalRepositoryHandler {

    /**
     * Find all animals in database by selected zone's name.
     *
     * @param zoneName Name of provided zone.
     *
     * @return Iterable object with all founded animals by selected zone's name.
     */
    Iterable<Animal> findAllByZoneName(String zoneName);

    /**
     * Find all animals in database by selected name
     *
     * @param animalName Name of provided animal.
     *
     * @return Iterable object with all founded animals by selected name
     */
    Iterable<Animal> findAllAnimalsByName(String animalName);

    /**
     * Add animal with provided body to database.
     *
     * @param animalReq Object of {@link AddAnimalReq Class} which hold request's information about name for animal, zone and
     * species.
     *
     * @return String with a message that animal has been added or throw {@link NoSuchElementException exception} if provided zone
     * or species name don't exist.
     *
     * @throws NoSuchElementException species or zone with provided name doesn't exist.
     */
    String addAnimal(AddAnimalReq animalReq) throws NoSuchElementException;

}
