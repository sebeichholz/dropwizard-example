package com.example.todoapi.db

import com.example.todoapi.core.Task
import com.google.common.base.Optional
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory

import javax.persistence.Table

/**
 * Created by orca on 13/06/2014.
 */
class TaskDAO extends AbstractDAO<Task>{

    TaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    public Optional<Task> findById(Long id) {
        return Optional.fromNullable(get(id))
    }

    public Task create(Task aTask) {
        return persist(aTask)
    }

    public void destroy(Task aTask) {
        currentSession().delete(aTask)
    }

    public Task updateWithAttributes(Task task, Map attrs){
        attrs.each { k, v ->
            if (['name','description','completed'].any { it.equals(k)}) task.putAt(k,v)
        }
        return task
    }
    public List<Task> findAll(){
        return list(namedQuery("Task.findAll"))
    }
    public List<Task> findCompletedTasks(){
        return list(namedQuery("Task.completed"))
    }
    public List<Task> findIncompleteTasks(){
        return list(namedQuery("Task.incomplete"))
    }
}
