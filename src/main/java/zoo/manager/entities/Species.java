package zoo.manager.entities;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Species {

    @Id
    private UUID uuid;
    private String name;
    private double expenses;
}
