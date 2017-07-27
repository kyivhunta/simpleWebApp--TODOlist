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
import static com.tasklist.hm5.servlets_session.Sessionservlet_edit.*;
import java.util.List;

@WebServlet(name = "main",urlPatterns = "/session_main",loadOnStartup = 1)
public class Sessionservlet_main extends HttpServlet {

    private static final String INDEX_HTML = "/index.html";
    private static final String UTF_8 = "utf-8";
    private static final String PAGE2_JSP = "/page2.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(UTF_8);

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        HttpSession session = req.getSession(true);

        List<Task> taskList = ((List<Task>) session.getAttribute(TASKS));

        if (taskList == null) taskList = new ArrayList<>();


        if (name == null || name.isEmpty()) resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        else {

            Task task = new Task();
            task.setName(name);
            task.setDesription(description);
            task.setId(taskList.isEmpty()?taskList.size()+1:taskList.stream().map(task1 -> task1.getId()).max(Integer::compareTo).get()+1);

            taskList.add(task);
        }

        session.setAttribute(TASKS, taskList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(INDEX_HTML);

        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        List<Task> allTask = ((List<Task>) session.getAttribute(TASKS));
        if (allTask == null) {

            allTask = new ArrayList<>();
            session.setAttribute(TASKS, allTask);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PAGE2_JSP);
        requestDispatcher.forward(req, resp);

    }
}
