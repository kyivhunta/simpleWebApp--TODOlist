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

@WebServlet(name = "MainServlet", urlPatterns = "/taskevent", loadOnStartup = 1)
public class MainServlet extends HttpServlet {

    private static final String UTF_8 = "utf-8";
    private static final String INDEX_HTML = "/index.html";
    private static final String PAGE_JSP = "/page.jsp";
    private static final String ALL_TASKS = "allTasks";
    private DaoTask daoTask;

    @Override
    public void init() throws ServletException {

        daoTask = new DaoTaskImpl();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(UTF_8);

        String name = req.getParameter("name");
        String description = req.getParameter("description");


        if (name == null || name.isEmpty()) resp.sendError(HttpServletResponse.SC_BAD_REQUEST);

        Task task = new Task();
        task.setName(name);
        task.setDesription(description);

        daoTask.create(task);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(INDEX_HTML);
        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Task> allTask = daoTask.getAllTask();

        req.setAttribute(ALL_TASKS, allTask);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PAGE_JSP);

        requestDispatcher.forward(req, resp);

    }
}
