document.getElementById('save-button').addEventListener('click', function() {
    // 获取表单数据
    const formData = new FormData(document.getElementById('account-form'));
    const data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });

    // 模拟保存操作，可以替换为实际的API调用
    console.log('保存的数据：', data);

    // 保存成功提示
    alert('信息已保存');
});
