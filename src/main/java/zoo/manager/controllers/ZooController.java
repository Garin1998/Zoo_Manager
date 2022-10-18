package zoo.manager.controllers;

import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import zoo.manager.entities.Animal;
import zoo.manager.entities.Species;
import zoo.manager.entities.Zone;
import zoo.manager.handlers.AnimalRepositoryHandler;
import zoo.manager.handlers.SpeciesRepositoryHandler;
import zoo.manager.handlers.ZoneRepositoryHandler;
import zoo.manager.models.requests.AddAnimalReq;
import zoo.manager.models.responses.ZoneWithAnimalsCountedUpRes;
import zoo.manager.models.responses.ZoneWithHighestExpensesRes;

@RestController
@RequestMapping("/zoo_manager")
public class ZooController {

    private final ZoneRepositoryHandler zoneRepositoryHandler;
    private final SpeciesRepositoryHandler speciesRepositoryHandler;
    private final AnimalRepositoryHandler animalRepositoryHandler;

    public ZooController(ZoneRepositoryHandler zoneRepositoryHandler,
                         SpeciesRepositoryHandler speciesRepositoryHandler,
                         AnimalRepositoryHandler animalRepositoryHandler) {
        this.zoneRepositoryHandler = zoneRepositoryHandler;
        this.speciesRepositoryHandler = speciesRepositoryHandler;
        this.animalRepositoryHandler = animalRepositoryHandler;
    }

    @GetMapping(value = "/animal/all/byZone", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Animal> findAllAnimalsByZoneName(@RequestParam(value = "zoneName") String zoneName) {
        return animalRepositoryHandler.findAllByZoneName(zoneName);
    }

    @GetMapping(value = "/animal/all/byName", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Animal> findAllAnimalsByName(@RequestParam(value = "animalName") String animalName) {
        return animalRepositoryHandler.findAllAnimalsByName(animalName);
    }

    @GetMapping(value = "/zone/zoneWithHighestExpenses", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ZoneWithHighestExpensesRes findZoneWithHighestExpenses() {
        return zoneRepositoryHandler.findZonesWithTotalExpenses(PageRequest.of(0, 1)).iterator().next();
    }

    @GetMapping(value = "/zone/zoneWithLowestOccupants", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ZoneWithAnimalsCountedUpRes findZoneWithLowestOccupants() {
        return zoneRepositoryHandler.findZonesWithAnimalCountedUp(PageRequest.of(0, 1)).iterator().next();
    }

    @PostMapping(value = "/zone", consumes = "application/json", produces = "plain/text")
    @ResponseStatus(HttpStatus.CREATED)
    public String addZone(@RequestBody @Valid Zone zone) {
        return zoneRepositoryHandler.addZone(zone);
    }

    @PostMapping(value = "/species", consumes = "application/json", produces = "plain/text")
    @ResponseStatus(HttpStatus.CREATED)
    public String addSpecies(@RequestBody @Valid Species species) {
        return speciesRepositoryHandler.addSpecies(species);
    }

    @PostMapping(value = "/animal", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal addAnimal(@RequestBody @Valid AddAnimalReq animalReq) {
        return animalRepositoryHandler.addAnimal(animalReq);
    }

}
