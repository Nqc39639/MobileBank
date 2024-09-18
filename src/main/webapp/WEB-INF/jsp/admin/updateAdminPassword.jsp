<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>/css/public.css" media="all">
    <style>
        .layui-form-item {
            padding-left: 35%;
            line-height: 40px;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <h1 style="margin: 30px;text-align: center;">修改密码</h1>
        <hr style="margin-bottom: 30px;">
        <form class="layui-form layuimini-form" style="width: 65%">
            <div class="layui-form-item">
                <label class="layui-form-label required" for="oldPassword">旧密码：</label>
                <div class="layui-input-block">
                    <input type="password" id="oldPassword" name="oldPassword" lay-verify="required"
                           lay-reqtext="旧密码不能为空！" placeholder="请输入旧密码" value="" class="layui-input">
                    <tip>填写该账号目前的的密码。</tip>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required" for="newPassword">新密码：</label>
                <div class="layui-input-block">
                    <input type="password" id="newPassword" name="newPassword" lay-verify="required"
                           lay-reqtext="新密码不能为空！" placeholder="请输入新密码" value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required" for="againPassword">确认密码：</label>
                <div class="layui-input-block">
                    <input type="password" id="againPassword" name="againPassword" lay-verify="required"
                           lay-reqtext="两次输入不一致！" placeholder="请再次输入新密码" value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn"
                            style="width: 100px; margin-left: 30px;">确认修改
                    </button>
                    <button type="reset" class="layui-btn layui-btn-primary" style="width: 100px;margin-left: 80px;">
                        重置
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script rel="stylesheet" src="<%=basePath%>/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script rel="stylesheet" src="<%=basePath%>/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['form', 'layer', 'jquery'], function () {
        let form = layui.form, layer = layui.layer, $ = layui.jquery;

        // 进行注册操作
        form.on('submit(saveBtn)', function (data) {
            if (data.field.oldPassword === '') {
                layer.msg('请输入当前帐号的密码！');
                return false;
            }
            if (data.field.newPassword === '') {
                layer.msg('新密码不能为空！');
                return false;
            }
            if (data.field.againPassword !== data.field.newPassword) {
                layer.msg('两次密码不一致！');
                return false;
            }
            $.ajax({
                url: '<%=basePath%>/admin/updateAdminPassword',   // 请求的URL
                type: 'POST',   // 请求类型
                data: {
                    oldPassword: data.field.oldPassword,
                    newPassword: data.field.newPassword
                },
                // dataType: 'json',   // 预期服务器返回的数据类型
                success: function (result) {
                    // 根据请求返回的json数据判断是否登录成功
                    if (result.code === 0) {
                        layer.msg('修改成功！', {icon: 6}, function () {
                            window.location.href = '<%=basePath%>/admin/logout';
                        });
                    } else {
                        layer.msg('旧密码不正确！', {icon: 5});
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>