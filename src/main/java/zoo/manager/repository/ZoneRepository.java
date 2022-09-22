package zoo.manager.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zoo.manager.entities.Zone;
import zoo.manager.model.response.ZoneWithHighestExpenses;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    Optional<Zone> findZoneByName(String name);

    @Query(value =
        "SELECT new zoo.manager.model.response.ZoneWithHighestExpenses(z.uuid, z.name, SUM(s.expenses) AS EXPENSES) FROM Zone AS"
            + " z INNER JOIN Animal AS a ON z.uuid = a.zone.uuid INNER JOIN Species AS s ON a.species.uuid = s.uuid GROUP "
            + "BY z.uuid ORDER BY EXPENSES DESC")
    Iterable<ZoneWithHighestExpenses> findZonesWithTotalExpenses(PageRequest pageRequest);
}
