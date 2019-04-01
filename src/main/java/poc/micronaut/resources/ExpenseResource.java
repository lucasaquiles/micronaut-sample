package poc.micronaut.resources;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.spring.tx.annotation.Transactional;
import poc.micronaut.domain.Expense;
import poc.micronaut.domain.Payment;
import poc.micronaut.repository.ExpenseRepository;
import poc.micronaut.repository.PaymentRepository;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Secured("isAuthenticated()")
@Controller("/expense")
public class ExpenseResource {

    @Inject
    private ExpenseRepository repository;

    @Inject
    private PaymentRepository paymentRepository;

    public ExpenseResource() {

    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Expense> expenses() {
        return repository.listAll();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Expense expense(Long id) {
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Expense add(@Body Expense expense) {

        return repository.save(expense);
    }

    @Transactional
    @Post(value = "/{id}/payment", consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Expense addPayment(@Parameter Long id, @Body Payment payment) {

        Expense expense = repository.findById(id).orElseThrow(()-> new EntityNotFoundException());

        payment.setExpense(expense);
        paymentRepository.save(payment);

        return expense;
    }

    @Get(value = "/{id}/payment", consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Payment getPayment(@Parameter Long id) {

        Payment payment = paymentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());

        return payment;
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Expense update(Long id, @Body String text) {

        return null;
    }

}

