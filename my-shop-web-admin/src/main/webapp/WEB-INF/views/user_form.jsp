<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的商城 | 用户管理</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                ${tbUser.id == null? "新增":"编辑"}用户
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>

        <section class="content">

            <div class="col-xs-12">
                <div class="row">
                    <c:if test="${baseResult !=null}">
                        <div class="alert alert-${baseResult.status==200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>

                </div>
                <!-- Horizontal Form -->
                <div class="box box-info">

                    <!-- /.box-header -->
                    <!-- form start -->
                    <form:form id="inputForm" cssClass="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                        <form:hidden path="id"/>
                        <div class="box-body">
                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label">邮箱:</label>

                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required email" path="email" placeholder="请输入邮箱地址"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">密码:</label>

                                <div class="col-sm-10">
                                    <form:password cssClass="form-control required" path="password" placeholder="请输入密码"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">名称:</label>

                                <div class="col-sm-10">
                                    <form:input path="username" cssClass="form-control required" placeholder="请输入名称"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="phone" class="col-sm-2 control-label">手机:</label>

                                <div class="col-sm-10">
                                    <form:input path="phone" cssClass="form-control required mobile" placeholder="请输入手机号码"/>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </div>
                    </form:form>
                </div>
            </div>

        </section>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>

<script>

</script>
</body>
</html>
