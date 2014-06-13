package com.example.todoapi.resources

import com.example.todoapi.core.Task
import com.example.todoapi.db.TaskDAO
import io.dropwizard.hibernate.UnitOfWork

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TasksResource {

    private final TaskDAO tasksDAO

    public TasksResource(TaskDAO tasksDAO) {
        this.tasksDAO = tasksDAO
    }

    @POST
    @UnitOfWork
    public Task createTask(Task aTask) {
        return tasksDAO.create(aTask)
    }

    @GET
    @UnitOfWork
    public List<Task> listTasks() {
        return tasksDAO.findAll()
    }
}
