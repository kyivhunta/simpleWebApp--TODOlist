package com.tasklist.hm5.dao;

import com.tasklist.hm5.entity.Task;
import com.tasklist.hm5.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DaoTaskImpl implements DaoTask {


    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    public void create(Task task) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            currentSession.save(task);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }

    }

    public Task read(int id) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;
        Task task = null;

        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            task = currentSession.get(Task.class, id);

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }
        return task;
    }

    public void update(Task task) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;


        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            currentSession.update(task);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }
    }

    public void delete(Task task) {

        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;


        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            currentSession.remove(task);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }

    }

    public List<Task> getAllTask() {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = null;
        List<Task> taskList = null;

        try {
            transaction = currentSession.getTransaction();

            transaction.begin();

            Query<Task> namedQuery = currentSession.createNamedQuery("allTasks", Task.class);

            taskList = namedQuery.getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            currentSession.close();
        }
        return taskList;
    }
}
