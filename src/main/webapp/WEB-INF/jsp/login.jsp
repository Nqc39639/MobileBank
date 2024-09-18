<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<head>
    <meta charset="utf-8">
    <title>用户登录</title>
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>/framework/reset.css">
    <link rel="stylesheet" href="<%=basePath%>/css/login.css">
    <script src="<%=basePath%>/js/jQuery.min.js"></script>
    <script src="<%=basePath%>/layui/layui.js"></script>
</head>
<body>
<div class="wrapper">
    <header>
        <p>用户登录</p>
    </header>
    <ul class="formbox">
        <li><input type="text" id="account" placeholder="请输入账号"></li>
        <li><input type="password" id="password" placeholder="请输入密码"></li>
        <li><span id="msg" style="color: red; font-size: 3vw;"></span></li>
    </ul>
    <div class="btn">
        <button type="button" id="login" onclick="login()">登录</button>
    </div>
    <div class="btn">
        <button type="button" id="register" onclick="location.href='<%=basePath%>/user/registerPage'">注册</button>
    </div>
</div>
</body>
<script>
    function login() {
        let account = $("#account").val();
        let password = $("#password").val();
        if (account === '' && password === '') {
            $("#msg").html("请输入账号和密码！");
        } else if (account === '') {
            $("#msg").html("请输入账号！");
        } else if (password === '') {
            $("#msg").html("请输入密码！");
        } else {
            $.ajax({
                url: '<%=basePath%>/user/login',   // 请求的URL
                type: 'POST',   // 请求类型
                data: {   // 发送的数据
                    account: account,
                    password: password
                },
                // dataType: 'json',   // 预期服务器返回的数据类型
                success: function (result) {
                    // 根据请求返回的json数据判断是否登录成功
                    if (result === '0') {
                        $("#msg").html("用户名不存在！");
                    } else if (result === 'home') {
                        window.location.href = '<%=basePath%>/user/home';
                    } else if (result === '2') {
                        $("#msg").html("密码不正确！");
                    }
                }
            });
        }
    }
</script>
</html>
