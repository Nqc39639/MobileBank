<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" href="<%=basePath%>/css/adminLogin.css">
    <link rel="stylesheet" href="<%=basePath%>/layui/css/layui.css">
    <script src="<%=basePath%>/js/adminLogin.js" charset="utf-8"></script>
    <script rel="stylesheet" src="<%=basePath%>/layui/layui.js"></script>
</head>
<body>
<div class="container" id="login-box">
    <!--侧边框-->
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <img src="<%=basePath%>/images/logo-admin-login.png">
            </div>
        </div>
    </div>
    <!--登录框-->
    <div class="form-container sign-in-container">
        <form class="layui-form" action="/admin/login" method="post">
            <h1>登录</h1>
            <div class="txtb">
                <input type="text" id="account" name="account">
                <span data-placeholder="账号"></span>
            </div>
            <div class="txtb">
                <input type="password" id="password" name="password">
                <span data-placeholder="密码"></span>
            </div>
            <%--            <div style="text-align: center;color:firebrick">${login_msg}</div>--%>
            <a href="javascript:">忘记密码？</a>
            <button type="submit" lay-submit="" lay-filter="login" style="height: 35px">登录</button>
            <br>
            <button type="reset" style="height: 35px">重置</button>
        </form>
    </div>
</div>
</body>
<script>
    layui.use(['form', 'layer', 'jquery'], function () {
        var form = layui.form, layer = layui.layer, $ = layui.jquery;

        // 登录错误提示
        if (${msg == '0'}) {
            layer.msg('用户名不存在！', {icon: 5});
        } else if (${msg == '2'}) {
            layer.msg('密码不正确！', {icon: 5});
        }

        // 进行登录操作
        form.on('submit(login)', function (data) {
            if (data.field.account === '') {
                layer.msg('请输入账号！');
                return false;
            }
            if (data.field.password === '') {
                layer.msg('请输入密码！');
                return false;
            }
            return true;
        });
    });
</script>
<script>
    $(".txtb input").on("focus", function () {
        $(this).addClass("focus");
    })

    $(".txtb input").on("blur", function () {
        if ($(this).val() === '') $(this).removeClass("focus");
    })
</script>
</html>
