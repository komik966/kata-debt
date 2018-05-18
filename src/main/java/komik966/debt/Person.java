package komik966.debt;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.Map;

public class Person {

    private final DebtRepository debtRepository;

    private final String name;

    @Inject
    public Person(DebtRepository debtRepository, @Assisted String name) {
        this.debtRepository = debtRepository;
        this.name = name;
    }

    public void borrow(Person from, Integer moneyAmount) {
        debtRepository.registerDebt(this, from, moneyAmount);
    }

    public Integer getDebt(Person lender) {
        return debtRepository.fetchDebt(this, lender);
    }

    public Map<Person, Integer> listDebts() {
        return debtRepository.fetchDebts(this);
    }

    public Map<Person, Integer> listRedeemOptions() {
        return debtRepository.fetchRedeemOptions(this);
    }

    public void sellDebt(Person borrower, Person debtBuyer, Integer debtPrice) {
        debtRepository.sellDebt(this, borrower, debtBuyer, debtPrice);
    }

    public Map<Person, Map<Person, Integer>> listDebtBuyingOptions() {
        return debtRepository.fetchDebtBuyingOptions(this);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return name.equals(person.name);
    }
}
