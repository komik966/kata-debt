package komik966.debt;

import com.google.inject.Guice;
import komik966.debt.guice.DebtModule;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DebtRepositoryTest {

    private DebtRepository debtRepository;

    private PersonFactory personFactory;

    @Before
    public void setUp() {
        debtRepository = new DebtRepository();
        personFactory = Guice.createInjector(new DebtModule()).getInstance(PersonFactory.class);
    }

    @Test
    public void registerDebtBorrowerTwiceTheSameLender() {
        Person lender = personFactory.create("A");
        Person borrower = personFactory.create("B");
        debtRepository.registerDebt(borrower, lender, 10);
        debtRepository.registerDebt(borrower, lender, 10);

        assertThat(debtRepository.fetchDebt(borrower, lender)).isEqualTo(20);
    }

    @Test
    public void registerDebtBorrowerTwiceDifferentLenders() {
        Person firstLender = personFactory.create("A");
        Person secondLender = personFactory.create("B");
        Person borrower = personFactory.create("C");
        debtRepository.registerDebt(borrower, firstLender, 10);
        debtRepository.registerDebt(borrower, secondLender, 10);

        assertThat(debtRepository.fetchDebt(borrower, firstLender)).isEqualTo(10);
        assertThat(debtRepository.fetchDebt(borrower, secondLender)).isEqualTo(10);
    }

    @Test
    public void fetchDebtNonRegisteredBorrower() {
        Person lender = personFactory.create("A");
        Person borrower = personFactory.create("B");

        assertThat(debtRepository.fetchDebt(borrower, lender)).isZero();
    }

    @Test
    public void fetchDebtNonRegisteredLender() {
        Person firstLender = personFactory.create("A");
        Person secondLender = personFactory.create("B");
        Person borrower = personFactory.create("C");
        debtRepository.registerDebt(borrower, firstLender, 10);

        assertThat(debtRepository.fetchDebt(borrower, secondLender)).isZero();
    }
}
