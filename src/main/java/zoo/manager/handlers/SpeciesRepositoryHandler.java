package zoo.manager.handlers;

import java.util.Optional;
import zoo.manager.entities.Species;
import zoo.manager.exceptions.models.RecordDuplicateException;

public interface SpeciesRepositoryHandler {

    Iterable<Species> findAllSpecies();
    Optional<Species> findSpeciesByName(String name);
    String addSpecies(Species species) throws RecordDuplicateException;
}
