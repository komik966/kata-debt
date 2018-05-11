package komik966.debt;

import com.google.inject.Guice;
import komik966.debt.guice.DebtModule;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PathFinderTest {
    final private BorrowersGraph borrowersGraph = new BorrowersGraph();
    private Person pA;
    private Person pB;
    private Person pC;
    private Person pD;
    private Person pE;
    private Person pF;

    @Before
    public void setUp() {
        PersonFactory personFactory = Guice.createInjector(new DebtModule()).getInstance(PersonFactory.class);
        /*
         * +---+   60  +---+   50 +---+   40  +---+
         * | F | <---- | E | <--- | A | <---- | D |
         * +---+       +---+      +---+       +---+
         *                          | 10        ^
         *                          V           | 30
         *                        +---+  20   +---+
         *                        | B | ----> | C |
         *                        +---+       +---+
         */
        pA = personFactory.create("A");
        pB = personFactory.create("B");
        pC = personFactory.create("C");
        pD = personFactory.create("D");
        pE = personFactory.create("E");
        pF = personFactory.create("F");
        borrowersGraph.increaseEdgeValue(pA, pB, 40);
        borrowersGraph.increaseEdgeValue(pB, pC, 20);
        borrowersGraph.increaseEdgeValue(pC, pD, 30);
        borrowersGraph.increaseEdgeValue(pD, pA, 40);
        borrowersGraph.increaseEdgeValue(pA, pE, 50);
        borrowersGraph.increaseEdgeValue(pE, pF, 60);
    }

    @Test
    public void findPath() {
        PathFinder pathFinder = new PathFinder(borrowersGraph);
        pathFinder.findPath(pA, pD);
        assertThat(pathFinder.getFoundPath()).containsExactly(pA, pB, pC, pD);
    }

    @Test
    public void findPathCyclic() {
        PathFinder pathFinder = new PathFinder(borrowersGraph);
        pathFinder.findPath(pA, pA);
        assertThat(pathFinder.getFoundPath()).containsExactly(pA, pB, pC, pD, pA);
    }

    @Test
    public void findPathNonPresent() {
        PathFinder pathFinder = new PathFinder(borrowersGraph);
        pathFinder.findPath(pF, pC);
        assertThat(pathFinder.getFoundPath()).isEmpty();
    }
}