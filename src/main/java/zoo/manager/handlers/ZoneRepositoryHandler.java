package zoo.manager.handlers;

import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import zoo.manager.entities.Zone;
import zoo.manager.models.responses.ZoneWithAnimalsCountedUpRes;
import zoo.manager.models.responses.ZoneWithHighestExpensesRes;

public interface ZoneRepositoryHandler {
    Zone addZone(Zone zone);
    Optional<Zone> findZoneByName(String name);
    Iterable<Zone> findAllZones();
    Iterable<ZoneWithHighestExpensesRes> findZonesWithTotalExpenses(PageRequest pageRequest);
    Iterable<ZoneWithAnimalsCountedUpRes> findZonesWithAnimalCountedUp(PageRequest pageRequest);

}
