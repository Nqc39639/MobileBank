<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<head>

    <meta charset="UTF-8">
    <title>转账成功</title>
    <!--屏幕自适应-->
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <!--css引入-->
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/success.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/reset.css">
</head>
<body>
<div class="wrapper">
    <ul class="formbox">
        <li>
            <div class="content">
                <i class="fa fa-check-circle fa-5x"></i>
            </div>
        </li>
        <li>
            <div class="content">
                <h3>转账成功</h3>
            </div>
        </li>
        <hr class="first-hr">
        <li>
            <div class="details">
                <div class="one-line">
                    <div class="name">转账人：</div>
                    <div class="value">${sessionScope.payUserInfo.realname}</div>
                </div>
                <div class="one-line">
                    <div class="name">转出账户：</div>
                    <div class="value">${sessionScope.payAccountInfo.bankCardID}</div>
                </div>
                <div class="one-line">
                    <div class="name">收款人：</div>
                    <div class="value">${sessionScope.payeeUserInfo.realname}</div>
                </div>
                <div class="one-line">
                    <div class="name">转入账户：</div>
                    <div class="value">${sessionScope.payeeAccountInfo.bankCardID}</div>
                </div>
                <div class="one-line">
                    <div class="name">转账金额：</div>
                    <div class="value">${sessionScope.transferInfo.money}</div>
                </div>
                <div class="one-line">
                    <div class="name">转账时间：</div>
                    <div><fmt:formatDate value="${sessionScope.transferInfo.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                </div>
                <div class="one-line">
                    <div class="name">流水号：</div>
                    <div class="value">${sessionScope.transferInfo.orderNumber}</div>
                </div>
            </div>
        </li>
        <hr class="second-hr">
    </ul>
    <div class="button_index">
        <button type="button" onclick="location.href='<%=basePath%>/user/home'">返回首页</button>
    </div>
</div>
</body>
</html>
