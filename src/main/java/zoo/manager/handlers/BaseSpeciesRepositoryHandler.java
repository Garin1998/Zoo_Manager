package zoo.manager.handlers;

import java.util.Optional;
import java.util.StringJoiner;
import org.springframework.stereotype.Service;
import zoo.manager.entities.Species;
import zoo.manager.exceptions.models.RecordDuplicateException;
import zoo.manager.repositories.SpeciesRepository;

/**
 * Class, which is used for implementation of {@link SpeciesRepositoryHandler interface} and handle requests for species.
 *
 * @author Krzysztof Kubiś
 * @version 1.0
 * @since JDK 17
 */
@Service
public class BaseSpeciesRepositoryHandler implements SpeciesRepositoryHandler {

    private final SpeciesRepository speciesRepository;

    public BaseSpeciesRepositoryHandler(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Species> findSpeciesByName(String name) {
        return Optional.of(speciesRepository.findSpeciesByName(name)).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addSpecies(Species species) throws RecordDuplicateException {
        if (!checkIfSpeciesNameExists(species.getName())) {
            speciesRepository.save(species);
            StringJoiner sj = new StringJoiner(" ");
            return sj.add("Species").add(species.getUuid().toString()).add("has been added successfully").toString();
        }
        throw new RecordDuplicateException();
    }

    /**
     * Checks if species with specified name already exists in the database.
     *
     * @param name Name of the specified zone
     *
     * @return true value if Zone exists in database or false value if not.
     */
    public boolean checkIfSpeciesNameExists(String name) {
        return speciesRepository.existsSpeciesByName(name);
    }

}
