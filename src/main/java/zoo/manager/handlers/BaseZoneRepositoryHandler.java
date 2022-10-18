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

@Service
public class BaseZoneRepositoryHandler implements ZoneRepositoryHandler {

    private final ZoneRepository zoneRepository;

    public BaseZoneRepositoryHandler(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public String addZone(Zone zone)
        throws RecordDuplicateException {
        if (!checkIfZoneNameExists(zone.getName())) {
            zoneRepository.save(zone);
            StringJoiner sj = new StringJoiner(" ");
            return sj.add("Zone").add(zone.getUuid().toString()).add("has been added successfully").toString();
        }
        throw new RecordDuplicateException();
    }

    @Override
    public Optional<Zone> findZoneByName(String name) {
        return Optional.of(zoneRepository.findZoneByName(name)).orElse(null);
    }

    @Override
    public Iterable<ZoneWithHighestExpensesRes> findZonesWithTotalExpenses(PageRequest pageRequest) {
        return zoneRepository.findZonesWithTotalExpenses(pageRequest);
    }

    @Override
    public Iterable<ZoneWithAnimalsCountedUpRes> findZonesWithAnimalCountedUp(PageRequest pageRequest) {
        return zoneRepository.findZonesWithAnimalCountedUp(pageRequest);
    }

    public boolean checkIfZoneNameExists(String name) {
        return zoneRepository.existsZoneByName(name);
    }
}
