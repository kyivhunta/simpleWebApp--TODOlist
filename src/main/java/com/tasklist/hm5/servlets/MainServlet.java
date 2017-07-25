package com.tasklist.hm5.servlets;

import com.tasklist.hm5.dao.DaoTask;
import com.tasklist.hm5.dao.DaoTaskImpl;
import com.tasklist.hm5.entity.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet", urlPatterns = "/taskevent")
public class MainServlet extends HttpServlet {

    private DaoTask daoTask;

    @Override
    public void init() throws ServletException {

        daoTask = new DaoTaskImpl();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String name = req.getParameter("name");
        String description = req.getParameter("description");


        if (name == null || name.isEmpty()) resp.sendError(HttpServletResponse.SC_BAD_REQUEST);

        Task task = new Task();
        task.setName(name);
        task.setDesription(description);

        daoTask.create(task);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.html");

        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Task> allTask = daoTask.getAllTask();

        req.setAttribute("allTasks", allTask);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/page.jsp");

        requestDispatcher.forward(req, resp);

    }
}
