package zoo.manager.handlers;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.StringJoiner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zoo.manager.entities.Zone;
import zoo.manager.exceptions.models.RecordDuplicateException;
import zoo.manager.models.responses.ZoneWithAnimalsCountedUpRes;
import zoo.manager.models.responses.ZoneWithHighestExpensesRes;
import zoo.manager.repositories.ZoneRepository;

/**
 * Class, which is used for implementation of {@link ZoneRepositoryHandler interface} and handle requests for zone.
 *
 * @author Krzysztof Kubiś
 * @version 1.0
 * @since JDK 17
 */
@Service
public class BaseZoneRepositoryHandler implements ZoneRepositoryHandler {

    private final ZoneRepository zoneRepository;

    public BaseZoneRepositoryHandler(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addZone(Zone zone) throws RecordDuplicateException {
        if (!checkIfZoneNameExists(zone.getName())) {
            zoneRepository.save(zone);
            StringJoiner sj = new StringJoiner(" ");
            return sj.add("Zone").add(zone.getUuid().toString()).add("has been added successfully").toString();
        }
        throw new RecordDuplicateException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Zone> findZoneByName(String name) {
        return Optional.of(zoneRepository.findZoneByName(name)).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<ZoneWithHighestExpensesRes> findZonesWithTotalExpenses(PageRequest pageRequest) {
        return zoneRepository.findZonesWithTotalExpenses(pageRequest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<ZoneWithAnimalsCountedUpRes> findZonesWithAnimalCountedUp(PageRequest pageRequest) {
        return zoneRepository.findZonesWithAnimalCountedUp(pageRequest);
    }

    /**
     * Checks if zone with specified name already exists in database.
     *
     * @param name Name of the specified zone
     *
     * @return true value if Zone exists in database or false value if not.
     */
    public boolean checkIfZoneNameExists(String name) {
        return zoneRepository.existsZoneByName(name);
    }
}
