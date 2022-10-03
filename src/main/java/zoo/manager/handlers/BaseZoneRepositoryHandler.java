package zoo.manager.handlers;

import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zoo.manager.entities.Zone;
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
    public Zone addZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Optional<Zone> findZoneByName(String name) {
        return Optional.of(zoneRepository.findZoneByName(name)).orElse(null);
    }

    @Override
    public Iterable<Zone> findAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public Iterable<ZoneWithHighestExpensesRes> findZonesWithTotalExpenses(PageRequest pageRequest) {
        return zoneRepository.findZonesWithTotalExpenses(pageRequest);
    }

    @Override
    public Iterable<ZoneWithAnimalsCountedUpRes> findZonesWithAnimalCountedUp(PageRequest pageRequest) {
        return zoneRepository.findZonesWithAnimalCountedUp(pageRequest);
    }
}
