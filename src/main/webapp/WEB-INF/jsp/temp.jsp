<%@ page contentType="text/html;charset=UTF-8" pageEncoding="GBK" %>
<!DOCTYPE html>
<html lang="en">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<base href="<%=basePath%>">
<head>
    <title></title>
</head>
<body>
<script>
    $(document).ready(function () {
        $.ajax({
            url: '<%=basePath%>/transfer/recipient',
            type: 'GET',
            dataType: 'json',   // 预期返回的数据类型
            success: function (data) {   // data是返回的一个包含Map对象的List
                if (data.length !== 0) {   // data不为空
                    var recipientList = $('#recipientList').empty();   // 清空现有的<li>元素
                    // 使用一个对象来存储已经添加过的bankCardID
                    var bankCardIds = {};
                    // 遍历返回的List，为每个Map对象创建一个<li>元素
                    $.each(data, function (index, recipient) {
                        // 检查bankCardID是否已经添加过
                        if (!bankCardIds[recipient['bankCardID']]) {
                            bankCardIds[recipient['bankCardID']] = true;   // 标记为已添加
                            var listItem = $('<li>').append(
                                $('<div>').addClass('recipient').append(
                                    $('<h3>').text(recipient['bankCardID']),
                                    $('<p>').text(recipient['realname'])),
                                $('<i>').addClass('fa fa-angle-right fa-2x'),
                                $('<div>').addClass('deleteRecipient').style('display: none').text("删除").on('click', function () {   // 处理删除按钮的点击事件
                                    var listItemId = $(this).closest('.deleteRecipient').data('id');
                                    $.ajax({
                                        url: '<%=basePath%>/transfer/recipient/' + listItemId,
                                        type: 'DELETE',
                                        success: function (response) {
                                            // 删除成功，从DOM中移除listItem
                                            $(this).closest('.deleteRecipient').remove();
                                            // 显示成功消息或执行其他操作
                                        },
                                        error: function (jqXHR, textStatus, errorThrown) {
                                            // 处理错误情况
                                            console.error('Error: ' + textStatus, errorThrown);
                                        }
                                    });
                                })
                            ).on('click', function () {
                                alert('编辑' + recipient['bankCardID']);   // 处理编辑按钮的点击事件
                            }).on('touchmove touchend', function (e) {
                                // 使用e.touches和e.changedTouches来检测滑动的方向
                                var startX = e.touches[0].clientX;

                                // 绑定touchmove事件
                                function handleTouchMove(event) {
                                    var currentX = event.touches[0].clientX;
                                    var swipeDistance = currentX - startX;

                                    // 如果向左滑动距离超过阈值，显示删除选项
                                    if (swipeDistance < -100) {
                                        // $(this).find('deleteRecipient').addClass('swipeActive');
                                        $(this).find('deleteRecipient').css('display', '');
                                    }
                                }

                                // 绑定touchend事件以清理绑定
                                function handleTouchEnd() {
                                    $(this).off('touchmove', handleTouchMove);
                                    $(this).off('touchend', handleTouchEnd);

                                    // 如果需要，可以在这里添加逻辑来处理删除选项的点击或列表项的自动回位
                                }

                                // 绑定事件
                                $(this).on('touchmove', handleTouchMove);
                                $(this).on('touchend', handleTouchEnd);
                            })
                        }
                        recipientList.append(listItem);   // 将<li>元素添加到<ul>元素中
                        // recipientList.append(deleteListItem);   // 将<li>元素添加到<ul>元素中
                    })
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('Error: ' + textStatus, errorThrown);   // 处理请求失败的情况
            }
        })
    })
</script>
</body>
</html>