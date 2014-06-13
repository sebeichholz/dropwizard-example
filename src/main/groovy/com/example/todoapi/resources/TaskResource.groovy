package com.example.todoapi.resources

import com.example.todoapi.core.Task
import com.example.todoapi.db.TaskDAO
import com.google.common.base.Optional
import com.sun.jersey.api.NotFoundException
import io.dropwizard.hibernate.UnitOfWork
import io.dropwizard.jersey.params.LongParam

import javax.ws.rs.*
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
        withFoundTask(taskId) { tasksDAO.destroy(it) }
    }

    @PUT
    @UnitOfWork
    public updateTask(@PathParam("taskId") LongParam taskId, Map params){
        withFoundTask(taskId) { tasksDAO.updateWithAttributes(it, params) }
    }
    private Task withFoundTask(LongParam taskId, Closure yield){
        final Optional<Task> task = tasksDAO.findById(taskId.get())
        if (!task.isPresent()){
            throw new NotFoundException("No such task.")
        }
        return yield(task.get())
    }
}
