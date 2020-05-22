<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css">
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
                ${tbContent.id == null? "新增":"编辑"}内容
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
                    <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                        <form:hidden path="id"/>
                        <div class="box-body">
                            <div class="form-group">
                                <label for="categoryId" class="col-sm-2 control-label">父级类目:</label>

                                <div class="col-sm-10">
                                    <form:hidden path="categoryId"/>
                                    <input id="categoryName" class="form-control required" placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default">
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="title" class="col-sm-2 control-label">标题:</label>

                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="title" placeholder="标题"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="subTitle" class="col-sm-2 control-label">子标题:</label>

                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="subTitle" placeholder="子标题"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="titleDesc" class="col-sm-2 control-label">标题描述:</label>

                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="titleDesc" placeholder="标题描述"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="url" class="col-sm-2 control-label">链接:</label>

                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="url" placeholder="链接"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pic" class="col-sm-2 control-label">图片1:</label>

                                <div class="col-sm-10">
                                    <form:input Class="form-control required" path="pic" placeholder="图片1"/>
                                    <div id="dropz" class="dropzone">

                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pic2" class="col-sm-2 control-label">图片2:</label>

                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="pic2" placeholder="图片2"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">详情:</label>

                                <div class="col-sm-10">
<%--                                    <form:textarea cssClass="form-control required" path="content" placeholder="详情"/>--%>
                                    <form:input path="content"/>
                                    <div id="editor">
                                        <p>欢迎使用<b>wangEditor</b>富文本编辑器</p>

                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button id="btnSubmit" type="submit" class="btn btn-info pull-right">提交</button>
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
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>

<!--自定义模态框 -->
<sys:modal title="请选择" message="<ul id='myTree' class='ztree'></ul>" />

<script>

    $(function () {
        App.initZtree("/content/category/tree/data",["id"],function (nodes) {
            var node = nodes[0];
            $("#categoryId").val(node.id);
            $("#categoryName").val(node.name);
            $("#modal-default").modal("hide")
        });
        var E = window.wangEditor;
        var editor = new E("#editor");
        editor.create();

        $("#btnSubmit").bind("click",function () {
            var contentHtml = editor.txt.html();
            $("#content").val(contentHtml);

        });
    });




    Dropzone.autoDiscover = false;
    var myDropzone = new Dropzone("#dropz",{
        url:"/upload",
        paramName:"dropFile",
        dictDefaultMessage:'拖动文件至此或者点击上传',

    });

</script>
</body>
</html>
