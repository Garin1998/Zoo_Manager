package zoo.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import zoo.manager.entities.Species;
import zoo.manager.entities.Zone;
import zoo.manager.handler.SpeciesRepositoryHandler;
import zoo.manager.handler.ZoneRepositoryHandler;

@RestController
@RequestMapping("/zoo_manager")
public class ZooController {

    private final ZoneRepositoryHandler zoneRepositoryHandler;
    private final SpeciesRepositoryHandler speciesRepositoryHandler;

    public ZooController(ZoneRepositoryHandler zoneRepositoryHandler, SpeciesRepositoryHandler speciesRepositoryHandler) {
        this.zoneRepositoryHandler = zoneRepositoryHandler;
        this.speciesRepositoryHandler = speciesRepositoryHandler;
    }

    @PostMapping("/zone")
    @ResponseStatus(HttpStatus.CREATED)
    public Zone addZone(@RequestBody Zone zone) {
        return zoneRepositoryHandler.addZone(zone);
    }

    @GetMapping("/zone")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Zone> findAllZones() {
        return zoneRepositoryHandler.findAllZones();
    }

    @GetMapping("/species")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Species> findAllSpecies() {
        return speciesRepositoryHandler.findAllSpecies();
    }

    @PostMapping("/species")
    @ResponseStatus(HttpStatus.CREATED)
    public Species addSpecies(@RequestBody Species species) {
        return speciesRepositoryHandler.addSpecies(species);
    }

}
