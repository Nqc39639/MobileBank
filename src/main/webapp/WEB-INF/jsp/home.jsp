<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1"/> <!--屏幕自适应-->
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/home.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/footer.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/reset.css">
    <script src="<%=basePath%>/js/jQuery.min.js"></script>
</head>
<body>
<!--整个页面最外层的div-->
<div class="wrapper">
    <!--抬头设置-->
    <header>
        <i class="fa fa-gg-circle"></i>&nbsp;手机银行
    </header>
    <!--功能菜单区-->
    <ul class="menu">
        <li>
            <i class="fa fa-dollar fa-2x" style="color: #DEB887"></i>
            <p>支付</p>
        </li>
        <li onclick="location.href='<%=basePath%>/user/transferManagePage'">
            <i class="fa fa-random fa-2x" style="color: gold"></i>
            <p>转账</p>
        </li>
        <li>
            <i class="fa fa-desktop fa-2x" style="color: #0097FF"></i>
            <p>理财</p>
        </li>
        <li>
            <i class="fa fa-balance-scale fa-2x" style="color: #A52A2A"></i>
            <p>基金</p>
        </li>
        <li>
            <i class="fa fa-line-chart fa-2x" style="color: #ff0000"></i>
            <p>股指</p>
        </li>
        <li>
            <i class="fa fa-cc-visa fa-2x" style="color: #D2691E"></i>
            <p>信用卡</p>
        </li>
        <li>
            <i class="fa fa-space-shuttle fa-2x" style="color: #38CA73"></i>
            <p>无卡取款</p>
        </li>
        <li>
            <i class="fa fa-cc-discover fa-2x" style="color: #666666"></i>
            <p>更多</p>
        </li>
    </ul>
    <!--智能区-->
    <div class="special">
        <div class="text">
            <h3>金融小秘书&nbsp;&nbsp;&nbsp;智能提醒</h3>
            <div class="subText">
                <span><i class="fa fa-bell" style="color:#FF0000"></i>缴费提醒</span>
                &nbsp;&nbsp;&nbsp;
                <span><i class="fa fa-credit-card" style="color:#0097FF"></i>还款提醒</span>
            </div>
        </div>
        <div class="go">GO</div>
    </div>
    <!--广告区-->
    <div class="banner">

    </div>
    <!--底部功能区-->
    <ul class="footer">
        <li onclick="location.href='<%=basePath%>/user/home'">
            <i class="fa fa-home"></i>
            <p>首页</p>
        </li>
        <li>
            <i class="fa fa-compass"></i>
            <p>发现</p>
        </li>
<%--        <c:choose>--%>
<%--            <c:when test="${userInfo == null}">--%>
<%--                 <li onclick="location.href='<%=basePath%>/'"> --%>
<%--                     <i class="fa fa-user-o"></i> --%>
<%--                     <p>个人中心</p> --%>
<%--                 </li> --%>
<%--             </c:when> --%>
<%--             <c:otherwise> --%>
                <li onclick="location.href='<%=basePath%>/user/personPage'">
                    <i class="fa fa-user-o"></i>
                    <p>个人中心</p>
                </li>
                <%-- <li onclick="location.href='<%=basePath%>/account/findRelatedAccountByTel'"> --%>
                <%--     <i class="fa fa-user-o"></i> --%>
                <%--     <p>账户列表</p> --%>
                <%-- </li> --%>
<%--            </c:otherwise>--%>
<%--        </c:choose>--%>
    </ul>
</div>
</body>
</html>
