<%@ page import="org.springframework.ui.Model" %>
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
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>向他人转账</title>
    <link rel="stylesheet" href="<%=basePath%>/framework/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>/framework/reset.css">
    <link rel="stylesheet" href="<%=basePath%>/css/footer.css">
    <link rel="stylesheet" href="<%=basePath%>/css/transferToOthers.css">
    <script src="<%=basePath%>/js/jQuery.min.js"></script>
</head>
<body>
<div class="wrapper">
    <!-- 标题区 -->
    <header>
        <i class="fa fa-angle-left fa-2x" onclick="location.href='<%=basePath%>/transfer/transferPage'"></i>
        <p>向他人转账</p>
        <p></p>
    </header>
    <!-- 信息确认区 -->
    <ul class="transfer">
        <li>
            <p id="payAccountId" data-payAccountId="${payAccountInfo.id}">转出账户：${payAccountInfo.bankCardID}</p>
        </li>
        <li>
            <label for="payeeRealName">收款人姓名：</label><input type="text" id="payeeRealName">
        </li>
        <li>
            <label for="payeeBankCardID">收款人账户：</label><input type="text" id="payeeBankCardID">
        </li>
        <li>
            <label for="transferBalance">转账金额：</label><input type="number" id="transferBalance" placeholder="请输入转账金额">
        </li>
        <li>
            <input type="password" id="payPassword" placeholder="请输入支付密码">
        </li>
        <li><span id="msg" style="color: red; font-size: 15px;"></span></li>
    </ul>
    <!-- 提交按钮 -->
    <div class="button_sub">
        <button type="button" onclick="submit_transfer()">提交</button>
    </div>
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
    function submit_transfer() {
        let payAccountId = document.getElementById('payAccountId').getAttribute('data-payAccountId');
        let payeeRealName = document.getElementById('payeeRealName').value;
        let payeeBankCardID = document.getElementById('payeeBankCardID').value;
        let transferBalance = document.getElementById('transferBalance').value;
        let payPassword = document.getElementById('payPassword').value;
         $.ajax({
             url: '<%=basePath%>/transfer/transferToOthers',   // 请求的URL
             type: 'POST',   // 请求类型
             data: {   // 发送的数据
                 payAccountId: payAccountId,
                 payeeRealName: payeeRealName,
                 payeeBankCardID: payeeBankCardID,
                 transferBalance: transferBalance,
                 payPassword: payPassword
             },
             // dataType: 'json',   // 预期服务器返回的数据类型
             success: function (result) {
                 if (result === '3') {
                     $("#msg").html("收款人不存在！");
                 } else if (result === '4') {
                     $("#msg").html("收款账户未开户！");
                 } else if (result === '5') {
                     $("#msg").html("收款人或收款账户不正确！");
                 } else if (result === '6') {
                     $("#msg").html("不能向自己转账！");
                 } else if (result === '0') {
                     $("#msg").html("支付密码不正确！");
                 } else if (result === '2') {
                     $("#msg").html("余额不足！");
                 } else if (result === '1') {
                     window.location.href = '<%=basePath%>/transfer/successPage';
                 }
             }
         });
    }
</script>
</html>
