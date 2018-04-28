Feature: debt

  Scenario: register debt
    Given person "A" and person "B" are square
    When person "A" borrows $"10" from "B"
    Then person "A" owes person "B" $"10"