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
    <meta name="viewport" content="width=device-width,initial-scale=1"> <!--屏幕自适应-->
    <title>用户注册</title>
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>/framework/reset.css">
    <link rel="stylesheet" href="<%=basePath%>/css/register.css">
    <script src="<%=basePath%>/js/jQuery.min.js"></script>
</head>
<body>
<div class="wrapper">
    <!-- 标题区 -->
    <header>
        <i class="fa fa-angle-left fa-2x" onclick="location.href='<%=basePath%>/user/loginPage'"></i>
        <p>用户注册</p>
        <p></p>
    </header>
    <!-- 表单区域 -->
    <ul class="formbox">
        <li><label for="account">账号：</label><input type="text" id="account" placeholder="请输入账号"></li>
        <li><label for="password">密码：</label><input type="password" id="password" placeholder="请输入密码"></li>
        <li><label for="checkPassword">确认密码：</label><input type="password" id="checkPassword" placeholder="请再次输入密码"></li>
        <li><label for="realname">姓名：</label><input type="text" id="realname" placeholder="请输入您的真实姓名"></li>
        <li><label for="telephone">手机号：</label><input type="text" id="telephone" placeholder="请输入您的手机号"></li>
        <li><label for="IDCard">身份证号：</label><input type="text" id="IDCard" placeholder="请输入您的身份证号"></li>
        <li><label for="birthPlace">籍贯：</label><input type="text" id="birthPlace" placeholder="请输入您的籍贯"></li>
        <li><label for="address">住址：</label><input type="text" id="address" placeholder="请输入您的居住地址"></li>
        <li><span id="msg" style="color: red; font-size: 15px"></span></li>
    </ul>
    <div class="btn">
        <button type="button" id="register" onclick="register()">注册</button>
    </div>
</div>
</body>
<script>
    function register() {
        let account = $("#account").val();
        let password = $("#password").val();
        let checkPassword = $("#checkPassword").val();
        let realname = $("#realname").val();
        let telephone = $("#telephone").val();
        let IDCard = $("#IDCard").val();
        let birthPlace = $("#birthPlace").val();
        let address = $("#address").val();
        if (account === '') {
            $("#msg").html("请输入账号！");
        } else if (password === '') {
            $("#msg").html("请输入密码！");
        } else if (checkPassword !== password) {
            $("#msg").html("两次密码不一致！");
        } else if (realname === '') {
            $("#msg").html("请输入您的真实姓名！");
        } else if (IDCard === '') {
            $("#msg").html("请输入您的身份证号！");
        } else if (IDCard.length !== 18) {
            $("#msg").html("请输入有效的身份证号！");
        } else if (birthPlace === '') {
            $("#msg").html("请输入您的籍贯！");
        } else {
            $.ajax({
                url: '<%=basePath%>/user/register',   // 请求的URL
                type: 'POST',   // 请求类型
                data: {   // 发送的数据
                    account: account,
                    password: password,
                    realname: realname,
                    telephone: telephone,
                    IDCard: IDCard,
                    birthPlace: birthPlace,
                    address: address
                },
                // dataType: 'json',   // 预期服务器返回的数据类型
                success: function (result) {
                    // 根据请求返回的json数据判断是否登录成功
                    if (result === '2') {
                        $("#msg").html("用户名已存在！");
                    } else if (result === 'login') {
                        window.location.href = '<%=basePath%>/user/loginPage';
                    } else if (result === '0') {
                        $("#msg").html("注册失败！");
                    }
                }
            });
        }
    }
</script>
</html>
