package zoo.manager.handler;

import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import zoo.manager.entities.Zone;
import zoo.manager.model.response.ZoneWithAnimalsCountedUpRes;
import zoo.manager.model.response.ZoneWithHighestExpensesRes;

public interface ZoneRepositoryHandler {
    Zone addZone(Zone zone);
    Optional<Zone> findZoneByName(String name);
    Iterable<Zone> findAllZones();
    Iterable<ZoneWithHighestExpensesRes> findZonesWithTotalExpenses(PageRequest pageRequest);
    Iterable<ZoneWithAnimalsCountedUpRes> findZonesWithAnimalCountedUp(PageRequest pageRequest);

}
