package komik966.debt;

import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

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
        mergeCyclicDebt(borrower, lender);
    }

    private void mergeCyclicDebt(Person firstPerson, Person secondPerson) {
        Integer debtDiff = fetchDebt(firstPerson, secondPerson) - fetchDebt(secondPerson, firstPerson);
        if (debtDiff == 0) {
            borrowersGraph.computeIfPresent(
                    secondPerson,
                    (secondPersonKey, secondPersonLenders) -> {
                        secondPersonLenders.remove(firstPerson);
                        return secondPersonLenders;
                    }
            );
            borrowersGraph.computeIfPresent(
                    firstPerson,
                    (firstPersonKey, firstPersonLenders) -> {
                        firstPersonLenders.remove(secondPerson);
                        return firstPersonLenders;
                    }
            );
        } else if (debtDiff < 0) {
            borrowersGraph.computeIfPresent(
                    firstPerson,
                    (firstPersonKey, firstPersonLenders) -> {
                        firstPersonLenders.remove(secondPerson);
                        return firstPersonLenders;
                    }
            );
            borrowersGraph.computeIfPresent(
                    secondPerson,
                    (secondPersonKey, secondPersonLenders) -> {
                        secondPersonLenders.put(firstPerson, abs(debtDiff));
                        return secondPersonLenders;
                    }
            );
        } else {
            borrowersGraph.computeIfPresent(
                    secondPerson,
                    (secondPersonKey, secondPersonLenders) -> {
                        secondPersonLenders.remove(firstPerson);
                        return secondPersonLenders;
                    }
            );
            borrowersGraph.computeIfPresent(
                    firstPerson,
                    (firstPersonKey, firstPersonLenders) -> {
                        firstPersonLenders.put(secondPerson, debtDiff);
                        return firstPersonLenders;
                    }
            );
        }
    }

    Integer fetchDebt(Person borrower, Person lender) {
        if (!borrowersGraph.containsKey(borrower) || !borrowersGraph.get(borrower).containsKey(lender)) {
            return 0;
        }
        return borrowersGraph.get(borrower).get(lender);
    }
}
