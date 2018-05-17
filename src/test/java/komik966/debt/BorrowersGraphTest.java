package komik966.debt;

import com.google.inject.Guice;
import komik966.debt.guice.DebtModule;
import org.junit.Test;

import static org.junit.Assert.*;

public class BorrowersGraphTest {

    @Test
    public void findPaths() {
        BorrowersGraph borrowersGraph = new BorrowersGraph();
        PersonFactory personFactory = Guice.createInjector(new DebtModule()).getInstance(PersonFactory.class);
        /*
         * +---+   60  +---+   50 +---+   40  +---+
         * | F | <---- | E | <--- | A | <---- | D |
         * +---+       +---+      +---+       +---+
         *               | 70       | 10        ^
         *               V          V           | 30
         *             +---+      +---+  20   +---+
         *             | G |      | B | ----> | C |
         *             +---+      +---+       +---+
         */
        Person pA = personFactory.create("A");
        Person pB = personFactory.create("B");
        Person pC = personFactory.create("C");
        Person pD = personFactory.create("D");
        Person pE = personFactory.create("E");
        Person pF = personFactory.create("F");
        Person pG = personFactory.create("G");
        borrowersGraph.increaseEdgeValue(pA, pB, 10);
        borrowersGraph.increaseEdgeValue(pB, pC, 20);
        borrowersGraph.increaseEdgeValue(pC, pD, 30);
        borrowersGraph.increaseEdgeValue(pD, pA, 40);
        borrowersGraph.increaseEdgeValue(pA, pE, 50);
        borrowersGraph.increaseEdgeValue(pE, pF, 60);
        borrowersGraph.increaseEdgeValue(pE, pG, 70);

        borrowersGraph.dfs(pA, (Person src, Person dest) -> {
        });
    }
}