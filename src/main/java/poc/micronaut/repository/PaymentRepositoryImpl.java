package poc.micronaut.repository;

import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.spring.tx.annotation.Transactional;
import poc.micronaut.domain.Expense;
import poc.micronaut.domain.Payment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class PaymentRepositoryImpl implements PaymentRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;

    public PaymentRepositoryImpl(EntityManager entityManager, ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @Transactional
    public Payment save(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Payment> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(Payment.class, id));
    }

    @Override
    public Optional<Payment> findByExpense(@NotNull Expense expense) {
        Query query = entityManager.createQuery("from Payment where expense = :expense");
        query.setParameter("expense", expense);
        Payment singleResult = (Payment) query.getSingleResult();
        return Optional.ofNullable(singleResult);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> listAll() {
        return entityManager.createQuery("from Payment").getResultList();
    }
}
