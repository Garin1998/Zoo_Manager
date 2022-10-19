package zoo.manager.controllers;

import java.util.NoSuchElementException;
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

/**
 * Class which is used as controller for REST service for repositories.
 *
 * @author Krzysztof Kubiś
 * @version 1.0
 * @since JDK 17
 */
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

    /**
     * Handle HTTP request method GET on /animal/all/byZone path, which return all animals in a database by specified zone name.
     *
     * @param zoneName Name of specified zone.
     *
     * @return Body of founded animals in database as iterable object of {@link Animal#Animal class}
     *
     * @see AnimalRepositoryHandler
     */
    @GetMapping(value = "/animal/all/byZone", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Animal> findAllAnimalsByZoneName(@RequestParam(value = "zoneName") String zoneName) {
        return animalRepositoryHandler.findAllByZoneName(zoneName);
    }

    /**
     * Handle HTTP request method GET on /animal/all/byName path, which return all animals in animals by specified name
     *
     * @param animalName Name of specified animal
     *
     * @return Body of founded animlas in database as iterable object of {@link Animal#Animal class}
     *
     * @see AnimalRepositoryHandler
     */
    @GetMapping(value = "/animal/all/byName", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Animal> findAllAnimalsByName(@RequestParam(value = "animalName") String animalName) {
        return animalRepositoryHandler.findAllAnimalsByName(animalName);
    }

    /**
     * Handle HTTP request method GET on /zone/zoneWithHighestExpense path, which return zone with the highest expenses.
     *
     * @return Object of {@link ZoneWithHighestExpensesRes Class} with zone, which have the highest expenses.
     *
     * @throws NoSuchElementException No records has been found
     */
    @GetMapping(value = "/zone/zoneWithHighestExpenses", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ZoneWithHighestExpensesRes findZoneWithHighestExpenses() throws NoSuchElementException {
        ZoneWithHighestExpensesRes foundedZones;
        try {
            foundedZones = zoneRepositoryHandler.findZonesWithTotalExpenses(PageRequest.of(0, 1)).iterator().next();
        } catch(NoSuchElementException ex) {
            throw new NoSuchElementException("No records has been found");
        }
        return foundedZones;
    }

    /**
     * Handle HTTP request method GET on /zone/zoneWithLowestOccupants path, which return zone with the lowest amount of
     * registered animals.
     *
     * @return Object of {@link ZoneWithHighestExpensesRes Class} with zone, which have the highest amount of registered animals.
     *
     * @throws NoSuchElementException No records has been found
     */
    @GetMapping(value = "/zone/zoneWithLowestOccupants", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ZoneWithAnimalsCountedUpRes findZoneWithLowestOccupants() throws NoSuchElementException {
        ZoneWithAnimalsCountedUpRes foundedZones;
        try {
            foundedZones = zoneRepositoryHandler.findZonesWithAnimalCountedUp(PageRequest.of(0, 1)).iterator().next();
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("No records has been found");
        }
        return foundedZones;
    }

    /**
     * Handle HTTP request method POST on /zone path, which add zone to database. Provide message with UUID of successfully
     * created zone.
     *
     * @param zone Object of {@link Zone Class} in JSON format
     *
     * @return String message of successfully created zone.
     */
    @PostMapping(value = "/zone", consumes = "application/json", produces = "plain/text")
    @ResponseStatus(HttpStatus.CREATED)
    public String addZone(@RequestBody @Valid Zone zone) {
        return zoneRepositoryHandler.addZone(zone);
    }

    /**
     * Handle HTTP request method POST on /species path, which add species to database. Provide message with UUID of successfully
     * created species.
     *
     * @param species Object of {@link Species Class} in JSON format.
     *
     * @return String message of successfully created zone.
     */
    @PostMapping(value = "/species", consumes = "application/json", produces = "plain/text")
    @ResponseStatus(HttpStatus.CREATED)
    public String addSpecies(@RequestBody @Valid Species species) {
        return speciesRepositoryHandler.addSpecies(species);
    }

    /**
     * Handle HTTP request method POST on /animal path, which add animal to database. Provide message with UUID of successfully
     * created animal. Produces and consumes JSON format.
     *
     * @param animalReq Object of {@link AddAnimalReq Class}, which contains zone name and species for further finding it existing
     * in a database.
     *
     * @return String message of successfully created zone.
     */
    @PostMapping(value = "/animal", consumes = "application/json", produces = "plain/text")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAnimal(@RequestBody @Valid AddAnimalReq animalReq) {
        return animalRepositoryHandler.addAnimal(animalReq);
    }

}
