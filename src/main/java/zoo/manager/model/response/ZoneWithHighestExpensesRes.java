package zoo.manager.model.response;

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
