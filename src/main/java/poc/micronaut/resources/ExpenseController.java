package poc.micronaut.resources;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import poc.micronaut.domain.Expense;
import poc.micronaut.repository.ExpenseRepository;

import java.util.ArrayList;
import java.util.List;


@Controller("/task")
public class ExpenseController {

    protected final ExpenseRepository repository;

    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Expense> expenses() {
        return new ArrayList<>();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Expense expense(Long id) {
        return null;
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Expense add(@Body Expense expense) {

        return expense;
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Expense update(Long id, @Body String text) {

        return null;
    }

}

