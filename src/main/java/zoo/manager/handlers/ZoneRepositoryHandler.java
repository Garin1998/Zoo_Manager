package zoo.manager.handlers;

import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import zoo.manager.entities.Zone;
import zoo.manager.exceptions.models.RecordDuplicateException;
import zoo.manager.models.responses.ZoneWithAnimalsCountedUpRes;
import zoo.manager.models.responses.ZoneWithHighestExpensesRes;

/**
 * Interface which is used for using methods implemented in {@link BaseZoneRepositoryHandler Class}
 *
 * @author Krzysztof Kubiś
 * @version 1.0
 * @since JDK 17
 */
public interface ZoneRepositoryHandler {

    /**
     * Add Zone with provided body to database.
     *
     * @param zone Object of {@link Zone Class}
     *
     * @return String with message or throw {@link RecordDuplicateException Exception} when already exist.
     *
     * @throws RecordDuplicateException Record already exist in DB.
     */
    String addZone(Zone zone) throws RecordDuplicateException;

    /**
     * Find zone by provided name.
     *
     * @param name Name of provided zone.
     *
     * @return Object of {@link Zone Class} or Null
     */
    Optional<Zone> findZoneByName(String name);

    /**
     * Find all zones and return information with sum its expenses per zone.
     *
     * @param pageRequest Object of {@link PageRequest CLass} with information about page and objects per page.
     *
     * @return Iterable object of {@link ZoneWithHighestExpensesRes Class} with all founded zone and its expenses. Amount of zones
     * is regulated by provided param with number of page and object per page.
     */
    Iterable<ZoneWithHighestExpensesRes> findZonesWithTotalExpenses(PageRequest pageRequest);

    /**
     * Find all Zones and return information with sum up every animal per zone.
     *
     * @param pageRequest Object of {@link PageRequest CLass} with information about page and objects per page.
     *
     * @return Iterable object of {@link ZoneWithHighestExpensesRes Class} with all founded zone and its expenses. Amount of zones
     * is regulated by provided param with number of page and object per page.
     */
    Iterable<ZoneWithAnimalsCountedUpRes> findZonesWithAnimalCountedUp(PageRequest pageRequest);

}
