package komik966.debt;

import cucumber.api.PendingException;
import cucumber.api.java8.En;

public class StepDefinitions implements En {

    public StepDefinitions() {
        Given("^person \"([^\"]*)\" and person \"([^\"]*)\" are square$", (String arg0, String arg1) -> {
            throw new PendingException();
        });
        When("^person \"([^\"]*)\" borrows \\$\"([^\"]*)\" from \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            throw new PendingException();
        });
        Then("^person \"([^\"]*)\" owes person \"([^\"]*)\" \\$\"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            throw new PendingException();
        });
    }
}
