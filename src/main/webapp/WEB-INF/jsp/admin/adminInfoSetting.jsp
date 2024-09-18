<%@ page import="com.mobileBank.pojo.AdminInfo" %>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<head>
    <meta charset="utf-8">
    <title>个人资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>/css/public.css" media="all">
    <script src="<%=basePath%>/js/jQuery.min.js"></script>
    <style>
        .layui-form-item {
            margin: 35px;
            padding-left: 35%;
        }
    </style>
</head>
<body style="background-color:#fff;">
<form class="layui-form layuimini-form" style="width: 65%; margin-top: 100px;">
    <input id="id" name="id" hidden>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="account">账号：</label>
        <div class="layui-input-block">
            <input type="text" id="account" value="${sessionScope.adminInfo.account}" class="layui-input"
                   disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="password">密码：</label>
        <div class="layui-input-block">
            <input type="text" id="password" value="${sessionScope.adminInfo.password}" class="layui-input"
                   disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="realname">姓名：</label>
        <div class="layui-input-block">
            <input type="text" id="realname" value="${sessionScope.adminInfo.realname}" class="layui-input"
                   disabled="disabled">
        </div>
    </div>
</form>
</body>
</html>
