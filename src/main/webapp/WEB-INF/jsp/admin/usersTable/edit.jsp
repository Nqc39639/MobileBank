<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<head>
    <meta charset="utf-8">
    <title>修改用户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>/css/public.css" media="all">
</head>
<body style="background-color:#fff;">
<form class="layui-form layuimini-form" style="width: 80%">
    <input id="id" name="id" hidden>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="account">账号：</label>
        <div class="layui-input-block">
            <input type="text" id="account" name="account" lay-verify="required" lay-reqtext="账号不能为空"
            placeholder="请输入账号" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="password">密码：</label>
        <div class="layui-input-block">
            <input type="text" id="password" name="password" lay-verify="required" lay-reqtext="密码不能为空"
            placeholder="请输入密码" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="realname">用户姓名：</label>
        <div class="layui-input-block">
            <input type="text" id="realname" name="realname" lay-verify="required" lay-reqtext="用户姓名不能为空"
                   placeholder="请输入用户姓名" value="" class="layui-input">
            <%--            <tip>填写自己管理账号的名称。</tip>--%>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="telephone">手机号码：</label>
        <div class="layui-input-block">
            <input type="text" id="telephone" name="telephone" placeholder="请输入手机号码" value="" maxlength="11"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="IDCard">身份证号：</label>
        <div class="layui-input-block">
            <input type="text" id="IDCard" name="IDCard" lay-verify="required" lay-reqtext="身份证号不能为空" placeholder="请输入身份证号" value="" minlength="18" maxlength="18"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required" for="birthPlace">户籍地：</label>
        <div class="layui-input-block">
            <input type="text" id="birthPlace" name="birthPlace" lay-verify="required" lay-reqtext="户籍地不能为空" placeholder="请输入户籍所在地" value="" class="layui-input" lay-verify="required" lay-reqtext="户籍不能为空">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" for="address">现居住地：</label>
        <div class="layui-input-block">
            <input type="text" id="address" name="address" placeholder="请输入居住地址" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户状态：</label>
        <div class="layui-input-block">
            <input type="radio" name="status" value="0" title="已注销">
            <input type="radio" name="status" value="1" title="正常">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
            <button type="reset" class="layui-btn layui-btn-primary" lay-filter="resetBtn">重置</button>
        </div>
    </div>
</form>
<script src="<%=basePath%>/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    const urlParams = new URLSearchParams(window.location.search);
    layui.use(['form', 'layer', 'jquery'], function () {
        let form = layui.form, layer = layui.layer, $ = layui.$;
        //监听提交
        form.on('submit(saveBtn)', function (data) {
            $.ajax({
                url: "<%=basePath%>/admin/update",
                type: "post",
                data: data.field,
                success: function (result) {
                    if (result.code === 0) {
                        layer.alert("更新成功!", {icon: 6}, function () {
                            let index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);   //关闭当前frame
                            parent.location.reload();   // 可以对父窗口进行刷新
                        });
                    } else {
                        layer.msg("Sorry, 更新失败了...", {icon: 5})
                    }
                }
            })
            return false;
        });
    });
</script>
</body>
</html>
