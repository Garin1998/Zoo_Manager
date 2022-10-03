package zoo.manager.handlers;

import zoo.manager.entities.Animal;
import zoo.manager.models.requests.AddAnimalReq;

public interface AnimalRepositoryHandler {
    Iterable<Animal> findAllAnimals();
    Iterable<Animal> findAllByZoneName(String zoneName);
    Iterable<Animal> findAllAnimalsByName(String animalName);
    Animal addAnimal(AddAnimalReq animalReq);

}
