package komik966.debt;

import com.google.inject.Guice;
import cucumber.api.java8.En;
import komik966.debt.guice.DebtModule;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class StepDefinitions implements En {

    public StepDefinitions() {
        PersonFactory personFactory = Guice.createInjector(new DebtModule()).getInstance(PersonFactory.class);
        Map<String, Person> people = new HashMap<>();
        Map<Person, Integer> debtsList = new HashMap<>();

        Given("^person \"([^\"]*)\" and person \"([^\"]*)\" are square$", (String firstPerson, String secondPerson) -> {
            people.put(firstPerson, personFactory.create(firstPerson));
            people.put(secondPerson, personFactory.create(secondPerson));

            assertThat(people.get(firstPerson).getDebt(people.get(secondPerson))).isZero();
            assertThat(people.get(secondPerson).getDebt(people.get(firstPerson))).isZero();
        });
        When("^person \"([^\"]*)\" borrows \\$\"([^\"]*)\" from \"([^\"]*)\"$", (String borrower, Integer moneyAmount, String lender) -> {
            people.get(borrower).borrow(people.get(lender), moneyAmount);
        });
        Then("^person \"([^\"]*)\" owes person \"([^\"]*)\" \\$\"([^\"]*)\"$", (String borrower, String lender, Integer moneyAmount) -> {
            assertThat(people.get(borrower).getDebt(people.get(lender))).isEqualTo(moneyAmount);
        });
        Given("^person \"([^\"]*)\" borrowed \\$\"([^\"]*)\" from person \"([^\"]*)\"$", (String borrower, Integer moneyAmount, String lender) -> {
            people.put(borrower, personFactory.create(borrower));
            people.put(lender, personFactory.create(lender));

            people.get(borrower).borrow(people.get(lender), moneyAmount);

            assertThat(people.get(borrower).getDebt(people.get(lender))).isEqualTo(moneyAmount);
        });
        Then("^person \"([^\"]*)\" owes nothing person \"([^\"]*)\"$", (String owner, String lender) -> {
            assertThat(people.get(owner).getDebt(people.get(lender))).isZero();
        });
        Given("^a person has some debts$", () -> {
            Person pA = personFactory.create("A");
            Person pB = personFactory.create("B");
            Person pC = personFactory.create("C");
            pA.borrow(pB, 20);
            pA.borrow(pC, 30);
            people.put("A", pA);
            people.put("B", pB);
            people.put("C", pC);
        });
        When("^he lists his debts$", () -> debtsList.putAll(people.get("A").listDebts()));
        Then("^his debts are on the list$", () -> {
            assertThat(debtsList).containsExactly(entry(people.get("B"), 20), entry(people.get("C"), 30));
        });
    }
}
