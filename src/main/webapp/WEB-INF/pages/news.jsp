<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css.css">
    <title>Новостная лента</title>
    <style>
        section {
            background: #fff;
            border-radius: 6px;
            color: #000000;
            display: block;
            padding: 24px 15px;
            text-align: center;
            float: left;
            width: 63%;
        }
        aside{
            background: #fff;
            border-radius: 6px;
            color: #000000;
            display: block;
            padding: 24px 15px;
            text-align: center;
            float: right;
            width: 30%;
        }
    </style>
</head>
<body>

<c:forEach  items = "${news}" var="list">
        <section>    <a href=${list.url}> ${list.title} </a> </section>
        <aside>      ${list.pubDate} </aside>
</c:forEach>

</body>
</html>
