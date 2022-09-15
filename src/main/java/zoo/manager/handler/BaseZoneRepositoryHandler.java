package zoo.manager.handler;

import org.springframework.stereotype.Service;
import zoo.manager.entities.Zone;
import zoo.manager.repository.ZoneRepository;

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
    public Iterable<Zone> findAllZones() {
        return zoneRepository.findAll();
    }
}
