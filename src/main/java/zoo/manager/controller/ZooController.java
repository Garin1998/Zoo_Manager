package zoo.manager.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import zoo.manager.handler.AnimalRepositoryHandler;
import zoo.manager.handler.SpeciesRepositoryHandler;
import zoo.manager.handler.ZoneRepositoryHandler;
import zoo.manager.model.request.AnimalReq;
import zoo.manager.model.response.ZoneWithHighestExpenses;

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

    @GetMapping("/zone/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Zone> findAllZones() {
        return zoneRepositoryHandler.findAllZones();
    }

    @GetMapping("/species/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Species> findAllSpecies() {
        return speciesRepositoryHandler.findAllSpecies();
    }

    @GetMapping("/animal/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Animal> findAllAnimals() {
        return animalRepositoryHandler.findAllAnimals();
    }

    @GetMapping("/animal/all/byZone")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Animal> findAllAnimalsByZoneName(@RequestParam(value = "zoneName") String zoneName) {
        return animalRepositoryHandler.findAllByZoneName(zoneName);
    }

    @GetMapping("/animal/all/byName")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Animal> findAllAnimalsByName(@RequestParam(value = "animalName") String animalName) {
        return animalRepositoryHandler.findAllAnimalsByName(animalName);
    }

    @GetMapping("/zone/zoneWithHighestExpenses")
    @ResponseStatus(HttpStatus.OK)
    public ZoneWithHighestExpenses findZoneWithHighestExpenses() {
        return zoneRepositoryHandler.findZonesWithTotalExpenses(PageRequest.of(0, 1)).iterator().next();
    }

    @PostMapping("/zone")
    @ResponseStatus(HttpStatus.CREATED)
    public Zone addZone(@RequestBody Zone zone) {
        return zoneRepositoryHandler.addZone(zone);
    }

    @PostMapping("/species")
    @ResponseStatus(HttpStatus.CREATED)
    public Species addSpecies(@RequestBody Species species) {
        return speciesRepositoryHandler.addSpecies(species);
    }

    @PostMapping("/animal")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal addAnimal(@RequestBody AnimalReq animalReq) {
        return animalRepositoryHandler.addAnimal(animalReq);
    }

}
