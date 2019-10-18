<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<head>

    <title>Список категорий</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%@include file="../templates/navbar.jsp" %>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        $(".all").on("change", function () {
            var groupId = $(this).data('id');
            $('.one[data-id="' + groupId + '"]').prop("checked", this.checked);
        });
        $(".one").on("change",function () {
            var groupId = $(this).data('id');
            var allChecked = $('.one[data-id="' + groupId +'"]:not(:checked)').length == 0;
            $('.all[data-id="'+groupId+'"]').prop("checked",allChecked);
        });
    });
</script>
<div class="container mt-5">
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
<form:form action="/category" method="post" modelAttribute="user" name="categoriesForm">
    <div class="container border border-primary rounded pb-3 pt-0 mt-0">
        <div class="row btn-group-toggle" data-toggle="buttons">
            <label class="btn btn-primary active btn-block btn-sm"  >
                Выбрать все <input type="checkbox" class="all" data-id="d1" checked autocomplete="on">
            </label>
        </div>
        <div class="row  ">
            <c:forEach var="category" items="${categories}">

                <div class="col-2 align-self-center item pt-3">
                  <label for="ckbx${category.id}">${category.topic}</label>
                </div>
                <div class="col-1 align-self-center item pt-3">
                    <form:checkbox path="selectedCategories" class="one" data-id="d1"
                                   value="${category.id}" id="ckbx${category.id}"/>
                    <script> function x(){
                            var c = document.getElementById('ckbx${category.id}');
                            if(${user.selectedCategories.contains(category.id)}) {
                                c.checked = true;
                            };
                    }
                        x();
                        </script>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="row pt-1 float-right btn-group">
        <div class="col">
            <button type="submit" name="delete" value="Удалить" formaction="/delete" class="btn btn-primary btn-block">Удалить</button>
        </div>
        <div class="col">
            <button type="submit" name="save" value="Сохранить" formaction="/category" class="btn btn-primary btn-block">Сохранить</button>
        </div>
    </div>
</form:form>
    </div>
</div>
</body>
</html>

