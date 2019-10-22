<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<div class="container float-right">
<form:form action="/add" method="POST" modelAttribute="category">
    <div class="form-group row">
        <form:label for="topic" path="topic" class="col-sm-1 col-form-label">Название</form:label>
        <div class="col-sm-5">
            <form:input type="text" name="topic" id="topic" path="topic" class="form-control"/>
        </div>
    </div>
    <div class="form-group row">
        <form:label for="url" path="url" class="col-sm-1 col-form-label">URL</form:label>
        <div class="col-sm-5">
            <form:input type="url" name="url" id="url" path="url" class="form-control"/>
        </div>
        <div class="form-group row">
            <form:button class="btn btn-primary " type="submit" >Добавить RSS-ленту</form:button>
        </div>
    </div>
</form:form>

