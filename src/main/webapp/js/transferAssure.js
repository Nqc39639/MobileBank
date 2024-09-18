function check_password(){
    var password = document.getElementById('password').value;
    if(password==null){
        alert('您输入的密码为空，请重新输入')
    }else{
        window.location.href='transfer/transferOk?payPassword='+password;
    }
}