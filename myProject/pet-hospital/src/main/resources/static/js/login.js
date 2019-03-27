
if (typeof(WX) == 'undefined') {
    WX = {};
}

WX.login = {
	flushImg : function() {
		var _url = "imagevcode?type=login&time=" + new Date().getTime();
		$(".vc-pic").attr('src', _url);
	},
	login : function() {
		// 将密码通过SHA256加密后传输
		var tmpUn = $('#username').val();
		var tmpPwd = $('#password').val();
		var tmpCode = $('#inputVcode').val();

		if (!tmpUn) {
			WX.login.showError(WX.login.defaults.usernameNull);
			return false;
		}
		if (!tmpPwd) {
            WX.login.showError(WX.login.defaults.pwdNull);
			return false;
		}
		if (!tmpCode) {
            WX.login.showError(WX.login.defaults.codeNull);
			return false;
		}
		tmpPwd = sha256_digest(tmpPwd);
		var loginData = 'username=' + tmpUn + '&password=' + tmpPwd
				+ '&verifyCode=' + tmpCode;
        $.ajax({
            type: 'POST',
            url: '/user/login',
            data: loginData,
            success: function (data) {
                if(data.success){
                    location.href='/';
                }else{
                    WX.login.flushImg();
                    $("#inputVcode").val("");
                    WX.login.showError(data.message);
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
                console.log("==============");
            }
        });
	},
    teacherLogin : function() {
        // 将密码通过SHA256加密后传输
        var tmpUn = $('#username').val();
        var tmpPwd = $('#password').val();
        var tmpCode = $('#inputVcode').val();

        if (!tmpUn) {
            WX.login.showError(WX.login.defaults.usernameNull);
            return false;
        }
        if (!tmpPwd) {
            WX.login.showError(WX.login.defaults.pwdNull);
            return false;
        }
        if (!tmpCode) {
            WX.login.showError(WX.login.defaults.codeNull);
            return false;
        }
        tmpPwd = sha256_digest(tmpPwd);
        var loginData = 'username=' + tmpUn + '&password=' + tmpPwd
            + '&verifyCode=' + tmpCode;
        $.ajax({
            type: 'POST',
            url: '/user/teacherLogin',
            data: loginData,
            success: function (data) {
                if(data.success){
                    location.href='/wx/orderStudent/list';
                }else{
                    WX.login.flushImg();
                    $("#inputVcode").val("");
                    WX.login.showError(data.resultText);
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
                console.log("==============");
            }
        });
    },
	showError : function(msg) {
		if (!msg) {
			return;
		}
		var obj = $("#error");
		obj.html(msg);
		obj.show();
	},
	showForgetError: function(msg) {
		if (!msg) {
			return;
		}
		var obj = $("#forgetErrors");
		obj.html(msg);
		obj.show();
	},
	forgetPwd: function() {
		var un = $('#forget_username').val();
		var email = $('#forget_email').val();
		if(!un){
			WX.login.showForgetError(WX.login.defaults.usernameNull);
			return false;
		}
		if(!email){
			WX.login.showForgetError(WX.login.defaults.emailNull);
			return false;
		}
		WX.ajax.post({
			url: $('#forgetForm').attr('action'),
			data : $('#forgetForm').serialize(),
			success : function(data) {
				if (data.success) {
					alert(data.resultText);
					window.location = $('base')[0].href + 'login';
				} else {
					WX.login.showForgetError(data.resultText);
				}
			},
		});
	},
	resetPwd: function() {
		// 将密码通过SHA256加密后传输
		var tmpPwd = $('#password').val();
		var tmpConPwd = $('#confirmPwd').val();
		var account = $('#account').val();
		var checkCode = $('#checkCode').val();
      	
		if(!tmpPwd){
			WX.login.showError(WX.login.defaults.passwordNull);
			return false;
		}
		if(!tmpConPwd){
			WX.login.showError(WX.login.defaults.confirmPwdNull);
			return false;
		}
		if(tmpPwd != tmpConPwd){
			WX.login.showError(WX.login.defaults.pwdNotEqual);
			return false;
		}
		tmpPwd = sha256_digest(tmpPwd);
		tmpConPwd = sha256_digest(tmpConPwd);
		var tmpData = 'password=' + tmpPwd + '&confirmPwd=' + tmpConPwd + '&account=' + account + '&checkCode=' + checkCode;
		WX.ajax.post({
			url: $('#resetForm').attr('action'),
			data: tmpData,
			success : function(data) {
				if (data.success) {
					alert(WX.login.defaults.confirm);
					window.location = $('base')[0].href + 'login';
				} else {
					WX.login.showError(data.resultText);
				}
			}
		});
	}
};
WX.login.defaults = {
	usernameNull : '用户名不能为空！',
	pwdNull : '密码或重复密码不能为空！',
	codeNull : '验证码不能为空！',
	emailNull: '邮箱不能为空！',
	passwordNull: '密码不能为空！',
	confirmPwdNull: '确认密码不能为空！',
	pwdNotEqual: '密码和确认密码不相等!',
	confirm: '重置密码成功，请重新登录！'
}