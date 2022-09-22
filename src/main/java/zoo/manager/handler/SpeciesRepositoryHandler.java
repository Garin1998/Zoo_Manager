package zoo.manager.handler;

import java.util.Optional;
import zoo.manager.entities.Species;

public interface SpeciesRepositoryHandler {

    Iterable<Species> findAllSpecies();
    Optional<Species> findSpeciesByName(String name);
    Species addSpecies(Species species);
}
