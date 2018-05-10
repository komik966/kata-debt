package komik966.debt;

import com.google.inject.Guice;
import komik966.debt.guice.DebtModule;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class PathFinderTest {
    private PathFinder pathFinder;
    private BorrowersGraph borrowersGraph;
    private Person pA;
    private Person pB;
    private Person pC;
    private Person pD;
    private Person pE;
    private Person pF;

    @Before
    public void setUp() {
        pathFinder = new PathFinder();
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
    public void findPath() throws PathFinder.BorrowerNotFound {
        List<Person> foundPath = pathFinder.findPath(borrowersGraph, pA, pB);
//        assertThat(foundPath).contains
    }
}