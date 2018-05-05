package komik966.debt;

import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
class DebtRepository {
    final private Map<Person, Map<Person, Integer>> borrowersGraph = new HashMap<>();

    void registerDebt(Person borrower, Person lender, Integer amountToBorrow) {
        borrowersGraph.computeIfPresent(
                borrower,
                (borrowerKey, lenderToBorrowedAmount) -> {
                    lenderToBorrowedAmount.computeIfPresent(
                            lender,
                            (lenderKey, borrowedAmount) -> borrowedAmount + amountToBorrow
                    );
                    lenderToBorrowedAmount.putIfAbsent(lender, amountToBorrow);
                    return lenderToBorrowedAmount;
                }
        );
        borrowersGraph.computeIfAbsent(
                borrower,
                borrowerKey -> {
                    Map<Person, Integer> lenderToBorrowedAmount = new HashMap<>();
                    lenderToBorrowedAmount.put(lender, amountToBorrow);
                    return lenderToBorrowedAmount;
                }
        );
    }

    Integer fetchDebt(Person borrower, Person lender) {
        if (!borrowersGraph.containsKey(borrower) || !borrowersGraph.get(borrower).containsKey(lender)) {
            return 0;
        }
        return borrowersGraph.get(borrower).get(lender);
    }
}
