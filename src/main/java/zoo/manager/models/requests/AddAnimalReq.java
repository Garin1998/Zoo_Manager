package zoo.manager.models.requests;

import lombok.Data;

@Data
public class AddAnimalReq {

    private String animalName;
    private String speciesName;
    private String zoneName;

}
