package komik966.debt;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

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

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
