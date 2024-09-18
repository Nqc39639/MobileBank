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
    <title>收款方列表</title>
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>/framework/reset.css">
    <link rel="stylesheet" href="<%=basePath%>/css/footer.css">
    <link rel="stylesheet" href="<%=basePath%>/css/recipientList.css">
    <script src="<%=basePath%>/js/jQuery.min.js"></script>
</head>
<body>
<div class="wrapper">
    <!-- 标题区 -->
    <header>
        <i class="fa fa-angle-left fa-2x" onclick="location.href='<%=basePath%>/user/transferManagePage'"></i>
        <p>收款方列表</p>
        <p></p>
        <%-- <p onclick="addRecipient.html">新增</p> --%>
    </header>
    <!-- 已经添加的收款人列表 -->
    <ul class="recipientList" id="recipientList">
        <c:choose>
            <c:when test="${empty recipientList}">
                <li>
                    <div class="recipient">查无记录</div>
                </li>
            </c:when>
            <c:otherwise>
                <c:forEach items="${recipientList}" var="recipient">
                    <li id="selectedRecipient" data-payeeAccountId="${recipient.id}" data-payeeRealname="${recipient.realname}"
                        data-payeeBankCardID="${recipient.bankCardID}" onclick="transferByRecipient()">
                        <div class="recipient">
                            <h3>${recipient.bankCardID}</h3>
                            <p>${recipient.realname}</p>
                        </div>
                        <i class="fa fa-angle-right"></i>
                    </li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ul>
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
            <p>账户</p>
        </li>
    </ul>
</div>
</body>
<script>
    function transferByRecipient() {
        let payeeAccountId = document.getElementById('selectedRecipient').getAttribute('data-payeeAccountId');
        let payeeRealname = document.getElementById('selectedRecipient').getAttribute('data-payeeRealname');
        let payeeBankCardID = document.getElementById('selectedRecipient').getAttribute('data-payeeBankCardID');
        $.ajax({
            url: '<%=basePath%>/transfer/recipient',   // 请求的URL
            type: 'POST',   // 请求类型
            data: {   // 发送的数据
                payeeAccountId: payeeAccountId,
                payeeRealname: payeeRealname,
                payeeBankCardID: payeeBankCardID
            },
            // dataType: 'json',   // 预期服务器返回的数据类型
            success: function (result) {
                if (result === 'success') {
                    window.location.href = '<%=basePath%>/transfer/transferByRecipientPage';
                } else {
                    window.location.href = '<%=basePath%>/user/loginPage';
                }
            }
        });
    }
</script>
</html>
