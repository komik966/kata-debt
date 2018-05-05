package komik966.debt;

import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Singleton
class DebtRepository {
    final private Map<Person, Set<Person>> borrowersGraph = new HashMap<>();

    void registerDebt(Person borrower, Person lender, Integer moneyAmount) {

    }

    Integer fetchDebt(Person borrower, Person lender) {
        return 0;
    }
}
