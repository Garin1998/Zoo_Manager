package zoo.manager.handler;

import zoo.manager.entities.Zone;

public interface ZoneRepositoryHandler {
    Zone addZone(Zone zone);
    Iterable<Zone> findAllZones();

}
