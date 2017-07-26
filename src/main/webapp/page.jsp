<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>OUR TASKS</title>
    <style type="text/css">
        table {
            border-collapse: collapse; /* Отображать двойные линии как одинарные */
        }

        th {
            background: #ccc; /* Цвет фона */
            text-align: left; /* Выравнивание по левому краю */
        }

        td, th {
            border: 1px solid black; /* Параметры границы */
            padding: 4px; /* Поля в ячейках */
        }
    </style>
</head>
<body>

<jsp:useBean id="allTasks" scope="request" type="java.util.List<com.tasklist.hm5.entity.Task>"/>
<div style="width: 700px">
    <c:if test="${empty allTasks}"><h2 align="center">You don't have any tasks!</h2></c:if>
    <c:if test="${!empty allTasks}">
    <h2 align="center">Your MySQL task</h2>
    <table style="border: solid" width="700">

            <tr>
                <th>ID</th>
                <th>Task</th>
                <th>Description</th>
                <th>Operations</th>
                <th>Status</th>
            </tr>

            <c:forEach items="${allTasks}" var="task">
                <h4>
                    <tr>
                        <td>${task.id}</td>
                        <td>${task.name}</td>
                        <td>${task.desription}</td>
                        <td><a href="${pageContext.request.contextPath}/delete?taskid=${task.id}">Delete</a></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/edit?task=${task.id}">
                                <c:if test="${!task.done}">In progress</c:if>
                                <c:if test="${task.done}">Done</c:if>
                            </a>
                        </td>
                    </tr>
                </h4>
            </c:forEach>
        </c:if>
    </table>

    <div style="margin-top: 50px" align="right"><a href="index.html">Go back...</a></div>
</div>

</body>
</html>
