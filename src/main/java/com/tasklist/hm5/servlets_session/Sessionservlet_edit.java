package com.tasklist.hm5.servlets_session;


import com.tasklist.hm5.entity.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "edit",urlPatterns = "/session_edit",loadOnStartup = 1)
public class Sessionservlet_edit extends HttpServlet{

    public static final String SESSION_MAIN = "/session_main";
    public static final String TASKS = "Tasks";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int taskID = Integer.parseInt(request.getParameter("taskid"));

        HttpSession session = request.getSession(true);

        List<Task> taskList = ((List<Task>) session.getAttribute("Tasks"));
        taskList.stream().filter(task -> task.getId() == taskID).forEach(task -> {
            if (task.isDone()) task.setDone(false);
            else task.setDone(true);
        });

        session.setAttribute(TASKS, taskList);
        request.getRequestDispatcher(SESSION_MAIN).forward(request, response);

    }
}
