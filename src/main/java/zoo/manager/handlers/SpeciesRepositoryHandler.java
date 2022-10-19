package zoo.manager.handlers;

import java.util.Optional;
import zoo.manager.entities.Species;
import zoo.manager.exceptions.models.RecordDuplicateException;

/**
 * Interface which is used for using methods implemented in {@link BaseSpeciesRepositoryHandler Class}
 *
 * @author Krzysztof Kubiś
 * @version 1.0
 * @since JDK 17
 */
public interface SpeciesRepositoryHandler {

    /**
     * Find species by provided name.
     *
     * @param name Name of provided species.
     *
     * @return Object of {@link Species Class} or Null
     */
    Optional<Species> findSpeciesByName(String name);

    /**
     * Add species with provided body to database.
     *
     * @param species Object of {@link Species Class}
     *
     * @return String with message or throw {@link RecordDuplicateException Exception} when already exist.
     *
     * @throws RecordDuplicateException Record already exist in DB.
     */
    String addSpecies(Species species) throws RecordDuplicateException;
}
