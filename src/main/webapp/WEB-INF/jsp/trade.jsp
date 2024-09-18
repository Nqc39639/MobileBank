<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>交易记录</title>
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>/framework/reset.css">
    <link rel="stylesheet" href="<%=basePath%>/css/footer.css">
    <link rel="stylesheet" href="<%=basePath%>/css/transfer.css">
</head>
<body>
<div class="wrapper">
    <!-- 标题区 -->
    <header>
        <i class="fa fa-angle-left fa-2x" onclick="location.href='<%=basePath%>/payAccount/findAccount'"></i>
        <p>交易记录</p>
        <p></p>
    </header>
    <!-- 交易记录信息区 -->
    <ul class="transfer">
        <!-- 交易账户信息 -->
        <li>
            <div class="account">
                <h3>账户：${payAccountInfo.bankCardID}</h3>
                <p>当前余额：<i class="fa fa-jpy"></i>${payAccountInfo.balance}</p>
            </div>
        </li>
        <c:choose>
            <c:when test="${empty payInfoList and empty transferInfoList and empty payeeInfoList}">
                <li>
                    <p>该账户暂未任何交易记录</p>
                </li>
            </c:when>
            <c:otherwise>
                <li><p>历史交易记录</p></li>
                <c:forEach items="${recipientList}" var="recipient">
                    <li id="recipientId" onclick="selectRecipient(${recipient.get("id")})">
                        <div class="account">
                            <p>收款人：${recipient.get("realname")}</p>
                            <i class="fa fa-angle-right"></i>
                        </div>
                        <p>收款账号：${recipient.get("bankCardID")}</p>
                        <p>转账日期：${recipient.get("payDate")}</p>
                    </li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ul>
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
<script>
    window.onload = function () {
        updateBalance();
    };

    function updateBalance() {
        let selectedOption = document.querySelector('#bankCardIDSelect option:checked');
        if (selectedOption) {
            // 获取data-balance属性值并赋值给id="balanceValue"的span标签，从而显示余额
            document.getElementById('balanceValue').textContent = selectedOption.getAttribute('data-balance');
        }
    }

    function selectRecipient(id) {
        let selectedPayAccountId = document.querySelector('#bankCardIDSelect option:checked').value;
        location.href = '<%=basePath%>/transfer/transferAssurePage?payAccountId=' + selectedPayAccountId + '&payeeAccountId=' + id;
    }

    function transferToOthers() {
        let selectedPayAccountId = document.querySelector('#bankCardIDSelect option:checked').value;
        location.href = '<%=basePath%>/transfer/transferToOthersPage?payAccountId=' + selectedPayAccountId;
    }
</script>
</html>
