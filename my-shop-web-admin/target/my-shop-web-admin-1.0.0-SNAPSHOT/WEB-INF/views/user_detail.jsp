<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的商城 | 用户详情</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body>
<div class="box-body">
    <table id="dataTable" class="table table-hover">
        <tbody>
            <tr>
                <td>邮箱:</td>
                <td>${tbUser.email}</td>
            </tr>
            <tr>
                <td>姓名:</td>

                <td>${tbUser.username}</td>
            </tr>
            <tr>
                <td>手机:</td>
                <td>${tbUser.phone}</td>
            </tr>
            <tr>
                <td>创建时间:</td>
                <td><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            </tr>
            <tr>
                <td>更新时间:</td>
                <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            </tr>
        </tbody>
    </table>

</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
