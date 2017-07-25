package com.tasklist.hm5.dao;

import com.tasklist.hm5.entity.Task;

import java.util.List;

public interface DaoTask {

    void create(Task task);

    Task read(int id);

    void update(Task task);

    void delete(Task task);

    List<Task> getAllTask();

}
