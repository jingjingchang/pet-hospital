function save() {
    var pwd = $("#password").val();
    var compwd = $("#compwd").val();
    if (pwd != compwd) {
        Messager.error("两次密码输入不一致！")
    } else{
        pwd=sha256_digest(pwd);
    $.ajax({
        type: 'POST',
        url: '/admin/sys/user/updateUserPwd',
        data: {
            id: $("#id").val(),
            pwd:pwd
        },
        success: function(data) {
            if (data.status) {
                Messager.success("操作成功！", function () {
                    location.reload();
                });
            } else {
                Messager.alert("操作失败！");
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