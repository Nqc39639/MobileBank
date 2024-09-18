<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<base href="<%=basePath%>">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>账户列表</title>
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>/framework/reset.css">
    <link rel="stylesheet" href="<%=basePath%>/css/accountList.css">
    <link rel="stylesheet" href="<%=basePath%>/css/footer.css">
    <script src="<%=basePath%>/js/jQuery.min.js"></script>
</head>
<body>
<!--最外层-->
<div class="wrapper">
    <!--抬头-->
    <header>
        <i class="fa fa-angle-left fa-2x" onclick="location.href='<%=basePath%>/user/personPage'"></i>
        <p>账户列表</p>
        <p onclick="location.href='<%=basePath%>/payAccount/addAccountPage'">新增</p>
    </header>
    <div>
        <br>
    </div>
    <c:choose>
        <c:when test="${payAccountInfoList.size()==0}">
            <div style="width: 100%">
                <h3 style="width:100%;text-align: center;">尚未添加可关联的账户</h3>
            </div>
        </c:when>
        <c:otherwise>
            <!--账户列表部分-->
            <ul class="accountList">
                <c:forEach items="${payAccountInfoList}" var="payAccountInfo">
                    <li>
                        <div class="account">
                            <h3>账户：${payAccountInfo.bankCardID}</h3>
                            <p>可用余额：<i class="fa fa-jpy"></i>${payAccountInfo.balance}</p>
                            <div class="operate" id="payAccountId" data-payAccountId="${payAccountInfo.id}">
                                <div class="obtn" onclick="trade()">
                                    <i class="fa fa-search"></i>交易记录
                                </div>
                                <div class="obtn" onclick="location.href='<%=basePath%>/transfer/transferPage'">
                                    <i class="fa fa-exchange"></i>转帐
                                </div>
                                <div class="obtn">
                                    <i class="fa fa-database"></i>信息设置
                                </div>
                                <div class="obtn" onclick="unlink()">
                                    <i class="fa fa-unlink"></i>解绑
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
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
<script>
    function trade() {
        let payAccountId = document.getElementById('payAccountId').getAttribute('data-payAccountId');
        window.location.href = '<%=basePath%>/payAccount/trade?payAccountId=' + payAccountId;
    }

    function unlink() {
        let payAccountId = document.getElementById('payAccountId').getAttribute('data-payAccountId');
        $.ajax({
            url: '<%=basePath%>/payAccount/unlink',   // 请求的URL
            type: 'POST',   // 请求类型
            data: {   // 发送的数据
                payAccountId: payAccountId
            },
            // dataType: 'json',   // 预期服务器返回的数据类型
            success: function (result) {
                // 根据请求返回的json数据判断是否登录成功
                if (result === '0') {
                    $("#msg").html("未解绑成功！");
                } else if (result === '1') {
                    window.location.href = '<%=basePath%>/payAccount/findAccount';
                }
            }
        });
    }
</script>
</body>