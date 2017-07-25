package com.tasklist.hm5.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "allTasks", query = "select task from Task AS task")
public class Task {
    private int id;
    private String name;
    private String desription;
    private boolean done;


    public Task(String name, String disription) {
        this.name = name;
        this.desription = disription;
    }

    public Task() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 225)
    public String getDesription() {
        return desription;
    }

    public void setDesription(String disription) {
        this.desription = disription;
    }

    @Basic
    @Column(name = "done", nullable = false)
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (desription != null ? !desription.equals(task.desription) : task.desription != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (desription != null ? desription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desription='" + desription + '\'' +
                '}';
    }
}
