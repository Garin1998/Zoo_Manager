package zoo.manager.models.responses;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ZoneWithAnimalsCountedUpRes {

    private UUID uuid;
    private String name;
    private long amount;
}
