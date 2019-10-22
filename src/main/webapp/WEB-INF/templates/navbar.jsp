<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page pageEncoding="UTF-8"%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/news">Новости</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/reset">Категории </a>
            </li>
        </ul>
    </div>
    <c:if test="${anon}">
    <div class="collapse navbar-collapse float-right" >
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/login">Войти</a>
            </li>
        </ul>
    </div>
    </c:if>
    <c:if test="${!anon}">
    <div class="collapse navbar-collapse float-right" >
        <ul class="navbar-nav ml-auto">
            <li class="nav-item mr-1">
                <h3>${username}</h3>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Выйти</a>
            </li>
        </ul>
    </div>
    </c:if>
</nav>