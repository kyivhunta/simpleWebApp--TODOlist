package com.tasklist.hm5.servlets_session;

import com.tasklist.hm5.entity.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "main",urlPatterns = "/session_main",loadOnStartup = 1)
public class sessionservlet_main extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        HttpSession session = req.getSession(true);

        List<Task> taskList = ((List<Task>) session.getAttribute("Tasks"));

        if (taskList == null) taskList = new ArrayList<>();


        if (name == null || name.isEmpty()) resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        else {

            Task task = new Task();
            task.setName(name);
            task.setDesription(description);
            task.setId(taskList.isEmpty()?taskList.size()+1:taskList.stream().map(task1 -> task1.getId()).max(Integer::compareTo).get()+1);

            taskList.add(task);
        }

        session.setAttribute("Tasks", taskList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.html");

        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        List<Task> allTask = ((List<Task>) session.getAttribute("Tasks"));

        if (allTask == null) {

            allTask = new ArrayList<>();
            session.setAttribute("Tasks", allTask);
        }

        req.setAttribute("allTasks", allTask);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/page2.jsp");

        requestDispatcher.forward(req, resp);

    }
}
