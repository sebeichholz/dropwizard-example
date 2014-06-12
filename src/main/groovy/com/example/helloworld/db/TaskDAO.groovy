package com.example.helloworld.db

import com.example.helloworld.core.Task
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

    public List<Task> findAll(){
        return list(namedQuery("Task.findAll"))
    }
}
