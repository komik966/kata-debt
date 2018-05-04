package komik966.debt;

import cucumber.api.PendingException;
import cucumber.api.java8.En;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions implements En {

    public StepDefinitions() {
        Person personA = new Person();
        Person personB = new Person();

        Given("^person \"([^\"]*)\" and person \"([^\"]*)\" are square$", (String firstPerson, String secondPerson) -> {
            assertThat(personA.getDebt(personB)).isZero();
            assertThat(personB.getDebt(personA)).isZero();
        });
        When("^person \"([^\"]*)\" borrows \\$\"([^\"]*)\" from \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            throw new PendingException();
        });
        Then("^person \"([^\"]*)\" owes person \"([^\"]*)\" \\$\"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            throw new PendingException();
        });
    }
}
