<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<head>
    <title></title>
    <meta charset="utf-8">
    <title>正常用户</title>
    <link rel="stylesheet" href="<%=basePath%>/lib/layui-v2.6.3/css/layui.css">
</head>
<body>
<div class="layuimini-main">
    <fieldset class="table-search-fieldset">
        <legend>搜索信息</legend>
        <div style="margin: 10px; text-align: center">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <div class="layui-inline" style="margin: 0 2%">
                        <label class="layui-form-label" style="width: 60px;" for="sex">性别</label>
                        <div class="layui-input-inline" style="width: 120px;">
                            <select id="sex" name="sex">
                                <option value="" selected>请选择…</option>
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin: 0 2%">
                        <label class="layui-form-label" style="width: 90px;">信用等级</label>
                        <div class="layui-input-inline" style="width: 60px;">
                            <input type="number" name="grade_min" autocomplete="off" class="layui-input" min="0"
                                   step="1"
                                   lay-affix="number">
                        </div>
                        <div class="layui-form-mid">-</div>
                        <div class="layui-input-inline" style="width: 60px;">
                            <input type="number" name="grade_max" autocomplete="off" class="layui-input" min="0"
                                   step="1"
                                   lay-affix="number">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin: 0 2%">
                        <label class="layui-form-label" style="width: 90px;" for="birthPlace">户籍地址</label>
                        <div class="layui-input-inline">
                            <input type="text" id="birthPlace" name="birthPlace" autocomplete="off" class="layui-input"
                                   style="width: 180px;">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="data-search-btn" style="width: 75px">
                            <i class="layui-icon"></i> 搜 索
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">添加</button>
            <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete">删除</button>
        </div>
    </script>
    <table id="usersTable" class="layui-table" lay-filter="users"></table>
    <script type="text/html" id="barDemo">
        <div class="layui-clear-space">
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="look">查看</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
        </div>
    </script>
</div>
<script src="<%=basePath%>/lib/layui-v2.6.3/layui.js"></script>
<script>
    layui.use(["table", "layer", "jquery", "form"], function () {
        let table = layui.table, layer = layui.layer, $ = layui.jquery, form = layui.form;

        //加载所有表项
        table.render({
            id: 'usersTable',
            cellMinWidth: 50,
            cols: [[
                {type: 'numbers', fixed: 'left'},
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', width: 60, title: 'ID', sort: true, totalRowText: '合计：', align: 'center'},
                {field: 'account', width: 120, title: '账号', align: 'center'},
                {field: 'password', width: 120, title: '密码', align: 'center'},
                {field: 'realname', width: 100, title: '用户姓名', align: 'center'},
                {
                    field: 'sex', width: 75, title: '性别', sort: true, align: 'center', templet: function (data) {
                        return data.sex === 1 ? '男' : '女';
                    }
                },
                {field: 'telephone', width: 120, title: '手机号码', align: 'center'},
                {field: 'IDCard', width: 180, title: '身份证号', align: 'center'},
                {field: 'birthPlace', width: 150, title: '户籍地', align: 'center'},
                {field: 'address', width: 150, title: '住址', align: 'center'},
                {field: 'creditGrade', width: 105, title: '信用等级', sort: true, align: 'center'},
                {title: '操作', minWidth: 180, toolbar: '#barDemo', align: "center", fixed: 'right'}

            ]],
            elem: '#usersTable', // 开启合计行
            error: function (res, msg) {
                console.log(res, msg)
            },
            limit: 10,
            totalRow: true,
            limits: [5, 10, 15],
            page: true,
            parseData: function (res) {
                let result;
                if (this.page.curr) {
                    result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
                } else {
                    result = res.data.slice(0, this.limit);
                }
                return {"code": res.code, "msg": res.msg, "count": res.count, 'totalRow': res.totalRow, "data": result};
            }, // 每页默认显示的数量
            toolbar: '#toolbarDemo',
            url: '<%=basePath%>/admin/readerNormalUser',
            method: "post"
        })

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            let result = JSON.stringify(data.field);

            //执行搜索重载
            table.reload('usersTable', {
                page: {
                    curr: 1
                },
                where: {
                    searchParams: result
                }
            }, 'data');
            return false;
        });

        //全选按钮判定
        table.on('checkbox(users)', function (obj) {
            let options = obj.config;   //获取当前表格属性配置项
            let checkStatus = table.checkStatus(options.id);   //获取选中行相关数据

            // 根据不同的事件名进行相应的操作
            switch (obj.event) {   //对应模板元素中的 lay-event 属性值
                case 'getCheckData':
                    let arr = checkStatus.data;
                    layer.alert(JSON.stringify(arr));
                    break;
                case 'getData':
                    layer.msg('getData');
                    break;
                case 'isAll':
                    let flag = checkStatus.isAll;
                    if (flag === true) layer.msg('全选');
                    else layer.msg('未全选');
                    break;
            }
        });

        //表单上方按钮功能
        table.on('toolbar(users)', function (obj) {
            if (obj.event === 'add') {   //监听添加操作
                let index = layer.open({
                    title: '添加用户信息',
                    type: 2,
                    shade: 0.5,
                    maxmin: true,
                    shadeClose: true,
                    area: ['50%', '90%'],   //长，高
                    content: '<%=basePath%>/admin/addUserInfoPage',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {   //监听删除操作
                let data = table.checkStatus('usersTable').data;
                if (data.length === 0) {
                    layer.msg("请选择您要删除的用户！");
                } else {
                    //获取id集合，进行删除工作
                    let array = [];
                    for (let i = 0; i < data.length; i++) {
                        array.push(data[i].id);
                    }
                    let idString = array.join(",");
                    //执行删除工作
                    layer.confirm('您确定要删除吗？', function (index) {
                        $.ajax({
                            url: "<%=basePath%>/admin/deleteNormalUserInfo",
                            type: "post",
                            data: {idString: idString},
                            success: function (result) {
                                if (result === "success") {
                                    layer.alert("删除成功！", {icon: 6});
                                    table.reload('usersTable', {});
                                } else {
                                    layer.msg("Sorry, 删除失败了...", {icon: 5});
                                }
                            }
                        })
                    });
                }
            }
        });

        //表单右方按钮功能
        table.on('tool(users)', function (obj) {
            let data = obj.data;   //得到当前行数据
            let index = obj.index;   //得到当前行索引
            let layEvent = obj.event;   //获得元素对应的 lay-event 属性值

            if (layEvent === 'delete') {   //删除特定项记录
                layer.confirm('确定删除吗？', function (index) {
                    $.ajax({
                        url: "<%=basePath%>/admin/deleteNormalUserInfo",
                        type: "post",
                        data: {idString: data.id},
                        success: function (result) {
                            if (result === "success") {
                                layer.alert("删除成功！", {icon: 6});
                                table.reload('usersTable', {});
                            } else {
                                layer.msg("Sorry, 删除失败了...", {icon: 5});
                            }
                        }
                    })
                    obj.del();   //删除对应行，并更新缓存
                    layer.close(index);
                });
            } else if (layEvent === 'edit') {
                layer.open({
                    type: 2,   // page 层类型
                    area: ['50%', '90%'],
                    title: '编辑',
                    shade: 0.6,   //遮罩透明度
                    shadeClose: true,   //点击遮罩区域，关闭弹层
                    maxmin: true,   //允许全屏最小化
                    anim: 0,   // 0-6 的动画形式，-1 不开启
                    content: '<%=basePath%>/admin/updateUserInfoPage',   //弹出框地址
                    success: function (layero, index) {
                        let body = layer.getChildFrame('body', index);
                        //获取content的窗口
                        let iframeWin = layero.find('iframe')[0].contentWindow;
                        body.find('#id').val(obj.data.id);
                        body.find('#account').val(obj.data.account);
                        body.find('#password').val(obj.data.password);
                        body.find('#realname').val(obj.data.realname);
                        body.find('#telephone').val(obj.data.telephone);
                        body.find('#IDCard').val(obj.data.IDCard);
                        body.find('#birthPlace').val(obj.data.birthPlace);
                        body.find('#address').val(obj.data.address);
                        body.find("input[name=status][value=1]").attr("checked", obj.data.status === 1);
                        body.find("input[name=status][value=0]").attr("checked", obj.data.status === 0);
                        iframeWin.layui.form.render();
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (layEvent === "look") {
                layer.open({
                    type: 2,   // page 层类型
                    area: ['50%', '90%'],
                    title: '查看',
                    shade: 0.6,   //遮罩透明度
                    shadeClose: true,   //点击遮罩区域，关闭弹层
                    maxmin: true,   //允许全屏最小化
                    anim: 0,   // 0-6 的动画形式，-1 不开启
                    content: '<%=basePath%>/admin/lookUserInfoPage',   //弹出框地址
                    success: function (layero, index) {
                        let body = layer.getChildFrame('body', index);
                        //获取content的窗口
                        let iframeWin = layero.find('iframe')[0].contentWindow;
                        console.log("data: ", obj.data);
                        body.find('#id').val(obj.data.id);
                        body.find('#account').val(obj.data.account);
                        body.find('#password').val(obj.data.password);
                        body.find('#realname').val(obj.data.realname);
                        body.find("input[name=sex][value=1]").attr("checked", obj.data.sex === 1);
                        body.find("input[name=sex][value=0]").attr("checked", obj.data.sex === 0);
                        body.find('#telephone').val(obj.data.telephone);
                        body.find('#IDCard').val(obj.data.IDCard);
                        body.find('#birthPlace').val(obj.data.birthPlace);
                        body.find('#address').val(obj.data.address);
                        body.find('#creditGrade').val(obj.data.creditGrade);
                        body.find("input[name=status][value=1]").attr("checked", obj.data.status === 1);
                        body.find("input[name=status][value=0]").attr("checked", obj.data.status === 0);
                        iframeWin.layui.form.render();
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
        });
    });
</script>
</body>
</html>
