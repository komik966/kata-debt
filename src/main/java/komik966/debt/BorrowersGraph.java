package komik966.debt;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Example graph "A borrows $10 from B":
 * +---+  Borrows $10 from  +---+
 * | A | -----------------> | B |
 * +---+                    +---+
 */
class BorrowersGraph {
    private HashMap<Person, Map<Person, Integer>> storage = new HashMap<>();

    boolean edgeExists(Person src, Person dest) {
        return storage.containsKey(src) && storage.get(src).containsKey(dest);
    }

    Integer getEdgeValue(Person src, Person dest) {
        return storage.get(src).get(dest);
    }

    List<Person> getAdjacentVertices(Person src) {
        return new ArrayList<>(storage.get(src).keySet());
    }

    void increaseEdgeValue(Person src, Person dest, Integer increaseBy) {
        createEdgeIfAbsent(src, dest);
        storage.get(src).compute(dest, (key, edgeValue) -> edgeValue + increaseBy);
    }

    void decreaseEdgeValue(Person src, Person dest, Integer decreaseBy) {
        createEdgeIfAbsent(src, dest);
        storage.get(src).compute(dest, (key, edgeValue) -> edgeValue - decreaseBy);
    }

    private void createEdgeIfAbsent(Person src, Person dest) {
        createVertexIfAbsent(src);
        createVertexIfAbsent(dest);
        storage.get(src).putIfAbsent(dest, 0);
    }

    private void createVertexIfAbsent(Person person) {
        storage.computeIfAbsent(person, k -> new HashMap<>());
    }

    Set<List<Person>> findPaths(Person src) {
        Set<List<Person>> foundPaths = new HashSet<>();
        Map<Person, Boolean> visited = storage.keySet().stream().collect(Collectors.toMap(Function.identity(), person -> false));

        Stack<Person> stack = new Stack<>();
        stack.push(src);

        while (!stack.empty()) {
            src = stack.pop();
        }

        return foundPaths;
    }
}
