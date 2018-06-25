var UserApply = function () {
    this.url = {
        addApply: "/weChat/wxEvent/addApply",
    };
    this.init = function () {
        this.validInit();
    }
    this.validInit  = function () {
        //登陆表单验证
        $("#userApply").validate({
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
    this.save = function () {
        var that = this;
        var valid = $('#userJobApply').valid();
        if (!valid) {
            return false;
        }
        ladda.ladda('start');
        $.ajax({
            type: 'POST',
            url: that.url.addApply,
            data: $("#userApply").serialize(),
            success: function (data) {
                ladda.ladda('stop');
                if(data.success){
                    Messager.success("提交成功！", function() {
                            location.href='/wx/getPartTimeJobList';
                    });
                }else{
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