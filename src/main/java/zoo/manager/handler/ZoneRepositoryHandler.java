package zoo.manager.handler;

import java.awt.print.Pageable;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import zoo.manager.entities.Zone;
import zoo.manager.model.response.ZoneWithHighestExpenses;

public interface ZoneRepositoryHandler {
    Zone addZone(Zone zone);
    Optional<Zone> findZoneByName(String name);
    Iterable<Zone> findAllZones();
    Iterable<ZoneWithHighestExpenses> findZonesWithTotalExpenses(PageRequest pageRequest);

}
