function save() {
    var pwd = $("#password").val();
    var compwd = $("#compwd").val();
    var oldpassword = $("#oldpassword").val();
    if (pwd != compwd) {
        Messager.error("两次密码输入不一致！")
    } else{
        pwd=sha256_digest(pwd);
        oldpassword=sha256_digest(oldpassword);
    $.ajax({
        type: 'POST',
        url: '/user/updateUserPwd',
        data: {
            id: $("#id").val(),
            oldPwd:oldpassword,
            pwd:pwd
        },
        success: function(data) {
            if (data.success) {
                Messager.success("操作成功！", function () {
                    location.href = '/login';
                });
            } else {
                Messager.error(data.message);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("==============");
            Messager.alert("操作失败！");
            console.log(XMLHttpRequest.responseText);
        }
    });
}

}