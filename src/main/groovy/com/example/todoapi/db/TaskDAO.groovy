package com.example.todoapi.db

import com.example.todoapi.core.Task
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory
import com.google.common.base.Optional

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

    public Task update(Map args){
        def task = args.task
        def params = args.with

        params.each { k, v ->
            if (['name','description','completed'].any { it.equals(k)}) task.putAt(k,v)
        }
    }
    public List<Task> findAll(){
        return list(namedQuery("Task.findAll"))
    }
}
