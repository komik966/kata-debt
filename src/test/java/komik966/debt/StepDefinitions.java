package komik966.debt;

import com.google.inject.Guice;
import cucumber.api.java8.En;
import komik966.debt.guice.DebtModule;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions implements En {

    public StepDefinitions() {
        PersonFactory personFactory = Guice.createInjector(new DebtModule()).getInstance(PersonFactory.class);
        Map<String, Person> people = new HashMap<>();

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
    }
}
