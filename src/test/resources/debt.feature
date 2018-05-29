Feature: debt

  Scenario: register debt
    Given person "A" and person "B" are square
    When person "A" borrows $"10" from "B"
    Then person "A" owes person "B" $"10"

  Scenario: settle debt
    Given person "A" borrowed $"25" from person "B"
    When person "B" borrows $"26" from "A"
    Then person "A" owes nothing person "B"
    And person "B" owes person "A" $"1"

  Scenario: transitive debt
    Given person "A" borrowed $"99" from person "B"
    And person "B" borrowed $"41" from person "C"
    When person "C" borrows $"30" from "A"
    Then person "A" owes person "B" $"69"
    And person "B" owes person "C" $"11"

  Scenario: list debts
    Given a person has some debts
    When he lists his debts
    Then his debts are on the list

  Scenario: return hint
    Given person "A" owes person "B"
    And person "B" owes person "C"
    When person "A" lists his redeem options
    Then he can give person "B" full amount
    And he can give person "C" amount owed by person "B"

  Scenario: Selling debt
    Given person "A" borrowed $"10" from person "B"
    And person "C" owes nothing person "B"
    When person "B" sells the debt "A" to person "C" for $"8"
    Then person "A" owes person "C" $"10"
    And person "C" owes person "B" $"8"
    And person "A" owes nothing person "B"

  Scenario: Listing debt buying options
    Given person "A" borrowed $"5" from person "E"
    And person "B" borrowed $"10" from person "C"
    And person "D" borrowed $"30" from person "E"
    When person "A" lists his debt buying options
    Then "B" is listed with $"10" due to "C"
    Then "D" is listed with $"25" due to "E"

#  Proszę zauważyć, że A nie może przejąć całego długu od D, bo samo jest dłużne E.
#  Może przejąć tylko różnicę między kwotami, czyli 30-5=25.

#  Scenario: Starting interest mode
#    Given person 'A' owes person 'B' $10
#    When the total debt has never been lower than $10 in last 6 months
#    Then this debt enters 'unfair' mode
#    And this debt will increase by 1% every day until fully paid
#
#  Scenario: Interest mode reset
#    Given person 'A' used to owe person 'B' in 'unfair' mode
#    And this debt was fully paid
#    When the person 'A' borrows money from person 'B' again
#    Then the debt is not in the 'unfair' mode