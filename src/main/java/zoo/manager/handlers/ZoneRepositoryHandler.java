package zoo.manager.handlers;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import zoo.manager.entities.Zone;
import zoo.manager.exceptions.models.RecordDuplicateException;
import zoo.manager.models.responses.ZoneWithAnimalsCountedUpRes;
import zoo.manager.models.responses.ZoneWithHighestExpensesRes;

public interface ZoneRepositoryHandler {

    String addZone(Zone zone) throws RecordDuplicateException;
    Optional<Zone> findZoneByName(String name);
    Iterable<ZoneWithHighestExpensesRes> findZonesWithTotalExpenses(PageRequest pageRequest);// throws NoSuchElementException;
    Iterable<ZoneWithAnimalsCountedUpRes> findZonesWithAnimalCountedUp(PageRequest pageRequest);// throws NoSuchElementException;

}
