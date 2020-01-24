<%@ page import="ru.azat.counter.model.Counter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
    <%Counter counter = (Counter) request.getAttribute("counter");%>
    Количество посещений данной страницы: <%=counter.getValue().toString()%>
</body>
</html>