package com.example.helloworld.resources

import com.example.helloworld.core.Task
import com.example.helloworld.db.TaskDAO
import com.google.common.base.Optional
import com.sun.jersey.api.NotFoundException
import io.dropwizard.hibernate.UnitOfWork
import io.dropwizard.jersey.params.LongParam
import org.hibernate.engine.spi.QueryParameters

import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * Created by orca on 13/06/2014.
 */
@Path("/task/{taskId}")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
    private final TaskDAO tasksDAO;

    public TaskResource(TaskDAO tasksDAO){
        this.tasksDAO = tasksDAO
    }

    @GET
    @UnitOfWork
    public Task getTask(@PathParam("taskId") LongParam taskId) {
        withFoundTask(taskId) { it }
    }

    @DELETE
    @UnitOfWork
    public deleteTask(@PathParam("taskId") LongParam taskId){
        withFoundTask(taskId.get()) { tasksDAO.destroy(it) }
    }

    @PUT
    @UnitOfWork
    public updateTask(@PathParam("taskId") LongParam taskId, Map params){
        withFoundTask(taskId) { tasksDAO.update(task:it, with:params) }
    }
    private Task withFoundTask(LongParam taskId, Closure yield){
        final Optional<Task> task = tasksDAO.findById(taskId.get())
        if (!task.isPresent()){
            throw new NotFoundException("No such task.")
        }
        return yield(task.get())
    }
}
