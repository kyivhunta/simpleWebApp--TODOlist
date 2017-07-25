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

@WebServlet(name = "RemoveServlet", urlPatterns = "/delete", loadOnStartup = 1)
public class RemoveServlet extends HttpServlet {

    private DaoTask daoTask;

    @Override
    public void init() throws ServletException {

        daoTask = new DaoTaskImpl();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int taskid = Integer.parseInt(request.getParameter("taskid"));

        if (daoTask.getAllTask().stream().anyMatch(task -> task.getId() == taskid)) {
            Task task = daoTask.read(taskid);
            daoTask.delete(task);
        }

        request.getRequestDispatcher("/taskevent").forward(request, response);

    }

}
