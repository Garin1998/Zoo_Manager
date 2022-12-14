package zoo.manager.models.responses;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ZoneWithHighestExpensesRes {

    private UUID uuid;
    private String name;
    private double totalExpenses;

}
