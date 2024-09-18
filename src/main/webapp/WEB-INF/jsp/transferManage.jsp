<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>转账管理</title>
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>/framework/reset.css">
    <link rel="stylesheet" href="<%=basePath%>/css/footer.css">
    <link rel="stylesheet" href="<%=basePath%>/css/transferManage.css">
</head>
<body>
<div class="wrapper">
    <!-- 标题区 -->
    <header>
        <i class="fa fa-angle-left fa-2x" onclick="location.href='<%=basePath%>/user/home'"></i>
        <p>转账管理</p>
        <p></p>
    </header>
    <!-- 功能列表区 -->
    <ul class="transferManageList">
        <li onclick="location.href='<%=basePath%>/transfer/recipientPage'">
            <i class="fa fa-address-book-o fa-2x"></i>
            <p>收款方管理</p>
        </li>
        <li onclick="location.href='<%=basePath%>/transfer/transferPage'">
            <i class="fa fa-random fa-2x"></i>
            <p>对外转账</p>
        </li>
        <li>
            <i class="fa fa-laptop fa-2x"></i>
            <p>掌银账户互转</p>
        </li>
    </ul>
    <!-- 底部功能区 -->
    <ul class="footer">
        <li onclick="location.href='<%=basePath%>/user/home'">
            <i class="fa fa-home"></i>
            <p>首页</p>
        </li>
        <li>
            <i class="fa fa-compass"></i>
            <p>发现</p>
        </li>
        <li onclick="location.href='<%=basePath%>/user/personPage'">
            <i class="fa fa-user-o"></i>
            <p>账户</p>
        </li>
    </ul>
</div>
</body>
</html>
