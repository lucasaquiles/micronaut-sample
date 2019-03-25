package poc.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Produces;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.ArrayList;


@Controller("/task")
public class TaskController {

	private static List<String> list = new ArrayList<>();

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> tasks() {
        return list;
    }

    @Get("/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    public String task(Integer index) {
        return list.get(index);
    }

    @Post(value = "/", consumes = MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String task(@Body String text) {
        list.add(text);
        return text;
    }

    @Put(value = "/{index}", consumes = MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String task(Integer index, @Body String text) {
        list.set(index, text);
        return text;
    }

}

