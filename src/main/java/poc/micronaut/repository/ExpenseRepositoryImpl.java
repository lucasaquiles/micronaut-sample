package poc.micronaut.repository;

import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.spring.tx.annotation.Transactional;
import poc.micronaut.domain.Expense;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Singleton
public class ExpenseRepositoryImpl implements ExpenseRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;

    public ExpenseRepositoryImpl(EntityManager entityManager, ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Transactional
    @Override
    public Expense save(@NotNull Expense expense) {
        entityManager.persist(expense);
        return expense;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Expense> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(Expense.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Expense> listAll() {
        return entityManager.createQuery("from Expense").getResultList();
    }
}
