package com.example.helloworld.core

import javax.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "tasks")
@NamedQueries([
    @NamedQuery(
            name = 'Task.findAll',
            query = "SELECT p FROM Task p"
    ),
    @NamedQuery(
            name = "Task.findById",
            query = "SELECT p FROM Task p WHERE p.id = :id"
    )
])
public class Task {

    static Timestamp currentTimestamp(){
        new Timestamp((new Date()).getTime())
    }

    //FIXME: createdAt is reset on every update
    Task(){
        this.createdAt = currentTimestamp()
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id

    @Column(name = 'name', nullable = false)
    String name

    @Column(name = 'description')
    String description

    @Column(name = 'created_at', nullable = false)
    Timestamp createdAt

    @Column(name = 'completed_at')
    Timestamp completedAt

    @Column(name = 'completed')
    boolean completed

    public getId() {
        id
    }
    public setId(Long anId) {
        id = anId
    }

    public String getCreatedAt(){
        createdAt.toString()
    }

    public String getCompletedAt(){
        completedAt ? completedAt.toString() : ""
    }

    public setCompleted(newCompleted){
        if (completed && !newCompleted) this.completedAt = null
        if (!completed && newCompleted) this.completedAt = currentTimestamp()

        this.completed = newCompleted
    }
}
