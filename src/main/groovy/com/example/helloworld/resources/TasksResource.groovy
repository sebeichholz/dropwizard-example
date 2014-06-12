package com.example.helloworld.resources;

/* import Person; */
/* import PersonDAO; */
/* import io.dropwizard.hibernate.UnitOfWork; */
/*  */
import javax.ws.rs.GET;
/* import javax.ws.rs.POST; */
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
// import java.util.List;
// import java.util.ArrayList;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TasksResource {

    /* private final PersonDAO peopleDAO; */

    public TasksResource() {
        // nothing here
    }

    /* @POST */
    /* @UnitOfWork */
    /* public Person createPerson(Person person) { */
    /*     return peopleDAO.create(person); */
    /* } */

    @GET
    /* @UnitOfWork */
    public List<String> listTasks() {
        ['foo', 'bar', 'baz']
    }

}
