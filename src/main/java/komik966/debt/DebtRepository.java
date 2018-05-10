package komik966.debt;

import com.google.inject.Singleton;

@Singleton
class DebtRepository {
    final private BorrowersGraph borrowersGraph = new BorrowersGraph();

    void registerDebt(Person borrower, Person lender, Integer amountToBorrow) {
        borrowersGraph.increaseEdgeValue(borrower, lender, amountToBorrow);
    }

    Integer fetchDebt(Person borrower, Person lender) {
        if (!borrowersGraph.edgeExists(borrower, lender)) {
            return 0;
        }
        return borrowersGraph.getEdgeValue(borrower, lender);
    }
}
