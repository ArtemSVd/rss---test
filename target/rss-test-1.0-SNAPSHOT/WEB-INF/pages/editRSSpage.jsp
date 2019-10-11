<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="checkboxModel" uri="http://www.springframework.org/tags/checkboxModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список категорий</title>
</head>
<body>

<checkboxModel:checkboxModel action="/edit" method="post" modelAttribute="selected">
    <c:forEach var="list" items="${categories}">
        ${list.topic}
        <checkboxModel:checkbox path="selected" value="${list.categoryId}" />
    </c:forEach>
    <input type="submit" value="submit"/>
</checkboxModel:checkboxModel>

</body>

</html>
