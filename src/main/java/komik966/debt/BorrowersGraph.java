package komik966.debt;

import java.util.HashMap;
import java.util.Map;

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

    void increaseEdgeValue(Person src, Person dest, Integer increaseBy) {
        createEdgeIfAbsent(src, dest);
        storage.get(src).compute(dest, (key, edgeValue) -> edgeValue + increaseBy);
    }

    private void createEdgeIfAbsent(Person src, Person dest) {
        createVertexIfAbsent(src);
        createVertexIfAbsent(dest);
        storage.get(src).putIfAbsent(dest, 0);
    }

    private void createVertexIfAbsent(Person person) {
        storage.computeIfAbsent(person, k -> new HashMap<>());
    }
}
