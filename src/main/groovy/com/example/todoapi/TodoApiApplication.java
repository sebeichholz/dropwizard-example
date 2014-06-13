package com.example.todoapi;

import com.example.todoapi.auth.ExampleAuthenticator;
import com.example.todoapi.core.Task;
import com.example.todoapi.db.TaskDAO;
import com.example.todoapi.resources.*;
import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class TodoApiApplication extends Application<TodoApiConfiguration> {
    public static void main(String[] args) throws Exception {
        new TodoApiApplication().run(args);
    }

    private final HibernateBundle<TodoApiConfiguration> hibernateBundle =
            new HibernateBundle<TodoApiConfiguration>(Task.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(TodoApiConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<TodoApiConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<TodoApiConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(TodoApiConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle());
    }

    @Override
    public void run(TodoApiConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {

        final TaskDAO dao = new TaskDAO(hibernateBundle.getSessionFactory());


        environment.jersey().register(new BasicAuthProvider<>(new ExampleAuthenticator(),
                                                              "SUPER SECRET STUFF"));

        environment.jersey().register(new TasksResource(dao));
        environment.jersey().register(new TaskResource(dao));

        environment.jersey().register(new ProtectedResource());
    }
}
