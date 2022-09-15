package zoo.manager.handler;

import zoo.manager.entities.Species;

public interface SpeciesRepositoryHandler {

    Iterable<Species> findAllSpecies();
    Species addSpecies(Species species);
}
