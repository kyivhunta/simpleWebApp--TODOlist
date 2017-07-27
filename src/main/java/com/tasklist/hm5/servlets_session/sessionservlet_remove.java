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
import java.util.stream.Collectors;


@WebServlet(name = "session_remove", urlPatterns = "/session_remove", loadOnStartup = 1)
public class sessionservlet_remove extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int taskid = Integer.parseInt(request.getParameter("taskid"));

        HttpSession session = request.getSession(true);

        List<Task> taskList = ((List<Task>) session.getAttribute("Tasks"));
        List<Task> collect = taskList.stream().filter(task -> task.getId() != taskid).collect(Collectors.toList());

        session.setAttribute("Tasks", collect);
        request.getRequestDispatcher("/session_main").forward(request, response);

    }
}
