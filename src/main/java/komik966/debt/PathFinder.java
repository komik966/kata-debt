package komik966.debt;

import java.util.*;

class PathFinder {
    private Set<Person> visited = new HashSet<>();

    private List<Person> foundPath = new ArrayList<>();

    private BorrowersGraph borrowersGraph;

    private int recursionLevel = 0;

    PathFinder(BorrowersGraph borrowersGraph) {
        this.borrowersGraph = borrowersGraph;
    }

    boolean findPath(Person src, Person dest) {
        if (recursionLevel > 0 && src == dest) {
            return true;
        }
        visited.add(src);
        recursionLevel++;
        for (Person adjacentVertex : borrowersGraph.getAdjacentVertices(src)) {
            if (
                    (!visited.contains(adjacentVertex) && findPath(adjacentVertex, dest))
                            || (adjacentVertex == dest)
                    ) {
                recursionLevel--;
                foundPath.add(adjacentVertex);
                if (recursionLevel == 0) {
                    foundPath.add(src);
                    Collections.reverse(foundPath);
                }
                return true;
            }
        }

        return false;
    }

    List<Person> getFoundPath() {
        return foundPath;
    }
}
