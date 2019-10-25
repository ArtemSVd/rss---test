<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<html>
<head>
    <style>
        img{
            width: 200px;
            height: 130px;
        }
    </style>
    <title>Новостная лента</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<jsp:include page="../templates/navbar.jsp"/>


<div class="container mt-5">
    <div class="row">
        <a class="btn btn-link" href="/prev ">Предыдущая страница </a>
        <a class="btn btn-link" href="/next ">Следующая страница</a>
    </div>
<c:forEach  items = "${newsList}" var="news" >
    <div class="row border-top border-primary p-3">
        <div class="col">
            <c:choose>
                <c:when test="${news.hasImage()}">
                    <img class="rounded float-right d-block img-fluid" src="${news.imageUrl}"/>
                </c:when>
                <c:otherwise>
                    <img class="rounded float-right d-block img-fluid"
                         src="https://xn--3-7sbdco5a0agkeii9o.xn--p1ai/wp-content/themes/marafon/images/no-photo.jpg"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col">    <a href="${news.url}" target="_blank"> ${news.title} </a> </div>
        <div class="col align-middle">      ${news.pubDate} </div>
    </div>
</c:forEach>
    <div class="row border-top border-primary p-3">
        <div class="col">
            <a class="btn btn-link" href="/prev ">Предыдущая страница </a>
            <a class="btn btn-link" href="/next ">Следующая страница</a>
        </div>
    </div>
</div>

</body>
</html>
