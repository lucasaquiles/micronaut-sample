package poc.micronaut.repository;

import poc.micronaut.domain.Expense;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {

    Expense save(Expense expense);

    Optional<Expense> findById(@NotNull Long id);

    List<Expense> listAll();
}
