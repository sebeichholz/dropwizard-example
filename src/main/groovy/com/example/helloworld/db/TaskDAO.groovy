package com.example.helloworld.db

import com.example.helloworld.core.Task
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory

/**
 * Created by orca on 13/06/2014.
 */
class TaskDAO extends AbstractDAO<Task>{

    TaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory)
    }

    public Task findById(Long id) {
        return get(id)
    }

    public long create(Task aTask) {
        return persist(aTask).getId()
    }

    public List findAll(){
        return list(namedQuery("Task.findAll"))
    }
}
