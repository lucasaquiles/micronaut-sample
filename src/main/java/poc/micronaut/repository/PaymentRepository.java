package poc.micronaut.repository;

import poc.micronaut.domain.Payment;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(@NotNull Long id);

    List<Payment> listAll();
}
