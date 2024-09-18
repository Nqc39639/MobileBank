function fun_login(){

    var telephone = document.getElementById('telephone').value;
    var password = document.getElementById('password').value;
    if(telephone==''){
        alert('手机号不能为空，请重新输入');
    }else if(password==''){
        alert('密码不能为空，请重新输入');
    }else{
        //客户端发出登录验证的请求操作
        window.location.href='mobile/login?telephone='+telephone+'&password='+password;
    }

}
//注册
function function_register(){
    var telephone = document.getElementById('telephone').value;
    var password = document.getElementById('password').value;
    var repassword = document.getElementById('repassword').value;
    if(telephone==''){
        alert('手机号不能为空，请重新输入');
    }else if(password==''){
        alert('密码不能为空，请重新输入');
    }else if(repassword==''){
        alert('确认密码不能为空，请重新输入');
    }else if(password!=repassword){
        alert('您两次输入的密码不同，请重新输入')
    }else{
        window.location.href='mobile/register?telephone='+telephone+'&password='+password;
    }
}