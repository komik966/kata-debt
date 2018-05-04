package komik966.debt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class DebtRepository {
    final private Map<Person, Set<Person>> borrowersGraph = new HashMap<>();
}
