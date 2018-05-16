package komik966.debt;

import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
class DebtRepository {
    final private BorrowersGraph borrowersGraph = new BorrowersGraph();

    void registerDebt(Person borrower, Person lender, Integer amountToBorrow) {
        borrowersGraph.increaseEdgeValue(borrower, lender, amountToBorrow);
        reduceCycle(borrower);
    }

    private void reduceCycle(Person borrower) {
        PathFinder pathFinder = new PathFinder(borrowersGraph);
        if (pathFinder.findPath(borrower, borrower)) {
            pathFinder.decreaseEachEdgeValueInFoundPath(pathFinder.minEdgeValueInFoundPath());
        }
    }

    Integer fetchDebt(Person borrower, Person lender) {
        if (!borrowersGraph.edgeExists(borrower, lender)) {
            return 0;
        }
        return borrowersGraph.getEdgeValue(borrower, lender);
    }

    Map<Person, Integer> fetchDebts(Person borrower) {
        return borrowersGraph.getAdjacentVertices(borrower).stream().collect(
                Collectors.toMap(Function.identity(), lender -> borrowersGraph.getEdgeValue(borrower, lender))
        );
    }

    Map<Person, Integer> fetchRedeemOptions(Person borrower) {
        return new HashMap<>();
    }
}
