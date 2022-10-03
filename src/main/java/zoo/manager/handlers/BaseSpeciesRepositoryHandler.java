package zoo.manager.handlers;

import java.util.Optional;
import java.util.StringJoiner;
import org.springframework.stereotype.Service;
import zoo.manager.entities.Species;
import zoo.manager.exceptions.models.RecordDuplicateException;
import zoo.manager.repositories.SpeciesRepository;

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
    public String addSpecies(Species species) throws RecordDuplicateException {
        if(!checkIfSpeciesNameExists(species.getName())) {
            speciesRepository.save(species);
            StringJoiner sj = new StringJoiner(" ");
            return sj.add("Species")
                     .add(species.getUuid().toString())
                     .add("has been added successfully")
                     .toString();
        }
        throw new RecordDuplicateException();
    }

    public boolean checkIfSpeciesNameExists(String name) {
        return speciesRepository.existsSpeciesByName(name);
    }

}
