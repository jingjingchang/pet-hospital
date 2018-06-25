var AddUserInfo = function () {
    this.url = {
        updateMember: "/weChat/wxEvent/addMemberInfo",
    };
    this.init = function () {
        this.validInit();
    }
    this.validInit  = function () {
        //登陆表单验证
        $("#memberInfo").validate({
            rules:{
                name:{
                    required:true,//必填
                    minlength:2, //最少6个字符
                    maxlength:32,//最多20个字符
                },
                mobile:{
                    required:true,//必填
                    minlength:11, //最少6个字符
                },
                email:{
                    required:true,
                    email:true,
                }
            },
            messages:{
                name:{
                    required:"必须填写用户名",
                    minlength:"用户名至少为3个字符",
                    maxlength:"用户名至多为32个字符",
                    remote: "用户名已存在",
                },
                email:{
                    required:"请输入邮箱地址",
                    email: "请输入正确的email地址"
                },
                mobile:{
                    required:"请输入手机号码",
                    digits:"请输入正确的手机号码",
                }
            }

        });
    }
    this.save = function (url) {
        var that = this;
        var valid = $('#memberInfo').valid();
        if (!valid) {
            return false;
        }
        ladda.ladda('start');
        $.ajax({
            type: 'POST',
            url: that.url.updateMember,
            data: $("#memberInfo").serialize(),
            success: function (data) {
                ladda.ladda('stop');
                if(data.success){
                    Messager.success("提交成功！", function() {
                        if(url){
                            location.href=url;
                        }else{
                            location.href='/wx/getPartTimeJobList';
                        }

                    });
                }else{
                    console.log("ajax");
                    Messager.alert(data.resultText);
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
                console.log("==============");
            }
        });
    };
}