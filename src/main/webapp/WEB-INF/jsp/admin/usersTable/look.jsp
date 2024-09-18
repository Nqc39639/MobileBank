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
    <title>查看用户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>/css/public.css" media="all">
    <script src="<%=basePath%>/js/jQuery.min.js"></script>
</head>
<body style="background-color:#fff;">
<form class="layui-form layuimini-form" style="width: 80%">
    <input id="id" name="id" hidden>
    <div class="layui-form-item">
        <label class="layui-form-label" for="account">账号：</label>
        <div class="layui-input-block">
            <input type="text" id="account" name="account" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="password">密码：</label>
        <div class="layui-input-block">
            <input type="text" id="password" name="password" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="realname">用户姓名：</label>
        <div class="layui-input-block">
            <input type="text" id="realname" name="realname" value="" class="layui-input">
            <%--            <tip>填写自己管理账号的名称。</tip>--%>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别：</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="1" title="男">
            <input type="radio" name="sex" value="0" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="telephone">手机号码：</label>
        <div class="layui-input-block">
            <input type="text" id="telephone" name="telephone" value="" maxlength="11" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="IDCard">身份证号：</label>
        <div class="layui-input-block">
            <input type="text" id="IDCard" name="IDCard" value="" minlength="18" maxlength="18"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="birthPlace">户籍地：</label>
        <div class="layui-input-block">
            <input type="text" id="birthPlace" name="birthPlace" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="address">现居住地：</label>
        <div class="layui-input-block">
            <input type="text" id="address" name="address" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="creditGrade">用户等级：</label>
        <div class="layui-input-block">
            <input type="number" id="creditGrade" name="creditGrade" value="" min="1" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户状态：</label>
        <div class="layui-input-block">
            <input type="radio" name="status" value="0" title="已注销">
            <input type="radio" name="status" value="1" title="正常">
        </div>
    </div>
</form>
</body>
</html>
