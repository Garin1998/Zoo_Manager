package zoo.manager.handler;

import zoo.manager.entities.Animal;
import zoo.manager.model.request.AnimalReq;

public interface AnimalRepositoryHandler {
    Iterable<Animal> findAllAnimals();
    Iterable<Animal> findAllByZoneName(String zoneName);
    Iterable<Animal> findAllAnimalsByName(String animalName);
    Animal addAnimal(AnimalReq animalReq);

}
