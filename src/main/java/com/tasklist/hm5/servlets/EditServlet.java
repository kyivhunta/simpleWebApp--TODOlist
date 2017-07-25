package com.tasklist.hm5.servlets;

import com.tasklist.hm5.dao.DaoTask;
import com.tasklist.hm5.dao.DaoTaskImpl;
import com.tasklist.hm5.entity.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditServlet", urlPatterns = "/edit", loadOnStartup = 1)
public class EditServlet extends HttpServlet {

    private DaoTask daoTask;



    @Override
    public void init() throws ServletException {
        daoTask = new DaoTaskImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int taskID = Integer.parseInt(request.getParameter("task"));

        Task task = daoTask.read(taskID);

        if (task.isDone())task.setDone(false);
        else task.setDone(true);

        daoTask.update(task);

        request.getRequestDispatcher("/taskevent").forward(request, response);

    }
}
