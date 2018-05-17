package komik966.debt.guice;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import komik966.debt.PersonFactory;

public class DebtModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().build(PersonFactory.class));
    }
}
