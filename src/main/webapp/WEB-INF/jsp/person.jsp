<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人信息中心</title>
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>/framework/reset.css">
    <link rel="stylesheet" href="<%=basePath%>/css/personCenter.css">
    <link rel="stylesheet" href="<%=basePath%>/css/footer.css">
    <script src="<%=basePath%>/js/jQuery.min.js"></script>
</head>
<body>
<!-- 整个页面最外层div -->
<div class="wrapper">
    <!-- 抬头区 -->
    <header>
        <p></p>
        <p>个人信息中心</p>
        <i class="fa fa-sign-out" onclick="location.href='<%=basePath%>/user/logout'">登出</i>
    </header>

    <!-- 账户信息 -->
    <section class="account">
        <br>
        <h2>账户</h2>
        <div class="account-details">
            <button type="button" onclick="location.href='<%=basePath%>/payAccount/findAccount'">点击查看详细账户信息</button>
        </div>
    </section>

    <!-- 个人信息 -->
    <section class="personal-info">
        <h2>个人信息</h2>
        <div class="info-details">
            <button type="button" onclick="location.href='personDetails.html'">点击查看获修改个人信息</button>
        </div>
    </section>

    <!-- 本月收支 -->
    <section class="monthly-balance">
        <h2>本月收支</h2>
        <div class="balance-details">
            <p>收入：<i class="fa fa-jpy"></i><span id="payeeBalance">${payeeBalance}</span></p>
            <p>支出：<i class="fa fa-jpy"></i><span id="payBalance">${payBalance}</span></p>
        </div>
    </section>

    <!-- 资产负债 -->
    <section class="assets-liabilities">
        <h2>资产负债</h2>
        <div class="assets-details">
            <p>总资产：<i class="fa fa-jpy"></i><span id="allBalance">${allBalance}</span></p>
            <p>总负债：<i class="fa fa-jpy"></i><span>0.00</span></p>
        </div>
    </section>

    <!-- 安全中心 -->
    <section class="security-center">
        <h2>安全中心</h2>
        <div class="security-details">
            <p onclick="location='paySet.html'">修改密码</p>
            <p>绑定设备管理</p>
            <p>安全问题设置</p>
        </div>
    </section>

    <!-- 我的网点 -->
    <%-- <section class="my-branches"> --%>
    <%--     <h2>我的网点</h2> --%>
    <%--     <div class="branches-details"> --%>
    <%--         <p>网点名称：北京支行</p> --%>
    <%--         <p>地址：北京市海淀区某某路</p> --%>
    <%--         <p>电话：010-12345678</p> --%>
    <%--     </div> --%>
    <%-- </section> --%>
    <div class="button-container">
        <button type="button" id="save-button">账号注销</button>
    </div>
    <br><br><br>
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
            <p>个人中心</p>
        </li>
    </ul>
</div>
</body>
<script>
</script>
</html>
