package komik966.debt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class PathFinder {
    List<Person> findPath(
            BorrowersGraph borrowersGraph,
            Person src,
            Person dest
    ) throws BorrowerNotFound {
        if (!borrowersGraph.containsKey(from)) {
            throw new BorrowerNotFound();
        }

        List<Person> path = new ArrayList<>();
        path.add(from);

        borrowersGraph.get(from).keySet().forEach(lender -> {
            try {
                path.addAll(findPath(borrowersGraph, lender, to));
            } catch (BorrowerNotFound borrowerNotFound) {
            }
        });


        return path;
    }

    class BorrowerNotFound extends Throwable {
    }
}
