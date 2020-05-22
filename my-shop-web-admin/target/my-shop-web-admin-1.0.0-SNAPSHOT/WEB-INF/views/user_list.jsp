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
                用户管理

            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">

                <div class="col-xs-12">
                    <div class="row">
                        <c:if test="${baseResult !=null}">
                            <div class="alert alert-${baseResult.status==200 ? "success" : "danger"} alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    ${baseResult.message}
                            </div>
                        </c:if>
                    </div>
                            <div class="box box-info-search" style="display: none;">
                                <div class="box-header">
                                    <h3 class="box-title">高级搜索</h3>
                                </div>
                                <div class="row form-horizontal" style="margin-top: 10px;">
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="username" class="col-sm-3 control-label">姓名:</label>

                                            <div class="col-sm-8">
                                                <input id="username" class="form-control" placeholder="姓名">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="email" class="col-sm-3 control-label">邮箱:</label>

                                            <div class="col-sm-8">
                                                <input id="email" class="form-control" placeholder="邮箱">
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="phone" class="col-sm-4 control-label">手机号:</label>

                                            <div class="col-sm-8">
                                                <input id="phone" class="form-control" placeholder="手机">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="padding-bottom: 10px;">
                                    <div class="col-xs-12 col-sm-9">
                                        <button type="button" class="btn btn-info pull-right" onclick="search()">搜索</button>
                                    </div>
                                </div>
                            </div>
                    <div class="box">
                            <h3 class="box-title">用户列表</h3>
                            <div class="row">
                                <div class="col-xs-12" style="margin-top: 10px;">
                                    <a href="/user/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-fw fa-plus"></i>新增</a>&nbsp;&nbsp;
                                    <button type="button" class="btn btn-sm btn-default" onclick="App.deleteMuli('/user/delete')"><i class="fa fa-fw fa-trash"></i>删除</button>
                                    <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-fw fa-download"></i>导入</a>&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-fw fa-upload"></i>导出</a>
                                    <button type="button" class="btn btn-primay" onclick="$('.box-info-search').css('display')== 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"><i class="fa fa-search"></i>搜索</button>
                                </div>
                            </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>电话</th>
                                    <th>邮箱</th>
                                    <th>更新日期</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
<%--                                <c:forEach items="${tbUsers}" var="tbUser">--%>
<%--                                    <tr>--%>
<%--                                        <td> <input id="${tbUser.id}" type="checkbox" class="minimal"></td>--%>
<%--                                        <td>${tbUser.id}</td>--%>
<%--                                        <td>${tbUser.username}</td>--%>
<%--                                        <td>${tbUser.phone}</td>--%>
<%--                                        <td>${tbUser.email}</td>--%>
<%--                                        <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>--%>
<%--                                        <td>--%>
<%--                                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-fw fa-search"></i>查看</a>&nbsp;&nbsp;--%>
<%--                                            <a href="#" type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-edit"></i>编辑</a>&nbsp;&nbsp;--%>
<%--                                            <a href="#" type="button" class="btn btn-sm btn-danger"><i class="fa fa-fw fa-trash"></i>删除</a>--%>

<%--                                        </td>--%>
<%--                                    </tr>--%>
<%--                                </c:forEach>--%>

                                </tbody>
                                <tfoot>

                                </tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>

            </div>

        </section>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>
<!--自定义模态框 -->
<sys:modal />

<script>
    var _dataTable;
$(function () {
    var _columns = [
            {
                "data":function (row,type,val,meta) {
                    return '<input id="'+row.id+'" type="checkbox" class="minimal">'
                }
            },
            { "data": "id" },
            { "data": "username" },
            { "data": "phone" },
            { "data": "email" },
            { "data": "updated" },
            {
                "data":function (row,type,val,meta) {
                    var detailUrl = "/user/detail?id=" + row.id;
                    return '<button type="button" class="btn btn-sm btn-default" onclick="App.showDetail(\''+detailUrl+'\') "><i class="fa fa-fw fa-search"></i>查看</button>&nbsp;&nbsp;' +
                        '<a href="/user/form?id='+row.id+' " type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-edit"></i>编辑</a>&nbsp;&nbsp;' +
                        '<a href="#" type="button" class="btn btn-sm btn-danger"><i class="fa fa-fw fa-trash"></i>删除</a>'
                }
            }
        ];
    _dataTable = App.initDataTables("/user/page",_columns);


});
function search() {

    var username = $("#username").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var param = {
        "username":username,
        "email":email,
        "phone":phone
    };

    _dataTable.settings()[0].ajax.data = param;
    _dataTable.ajax.reload();
}


</script>
</body>
</html>
