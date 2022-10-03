package zoo.manager.models.requests;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddAnimalReq {

    @NotBlank(message = "Please provide a name for animal.")
    private String animalName;
    @NotBlank(message = "Please provide species for a new animal.")
    private String speciesName;
    @NotBlank(message = "Please provide zone where your new animal will belong.")
    private String zoneName;

}
