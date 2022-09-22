package zoo.manager.handler;

import java.util.Optional;
import org.springframework.stereotype.Service;
import zoo.manager.entities.Species;
import zoo.manager.repository.SpeciesRepository;

@Service
public class BaseSpeciesRepositoryHandler implements SpeciesRepositoryHandler {

    private final SpeciesRepository speciesRepository;

    public BaseSpeciesRepositoryHandler(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    @Override
    public Iterable<Species> findAllSpecies() {
        return speciesRepository.findAll();
    }

    @Override
    public Optional<Species> findSpeciesByName(String name) {
        return Optional.of(speciesRepository.findSpeciesByName(name)).orElse(null);
    }

    @Override
    public Species addSpecies(Species species) {
        return speciesRepository.save(species);
    }
}
