/**
 * 
 * jQuery Bootstrap msg
 * 
 */
var Messager = {
	alert: function(msg, type, fn) {
		var opt = {confirmButtonText: '确定'};
		if (msg) {
			opt.title = msg;
		}
		if (type) {
			opt.type = type;
		}
		if (fn && fn instanceof Function) {
			swal(opt, fn);
		} else {
			swal(opt);
		}
	},
	success: function(msg, fn) {
		this.alert(msg, 'success', fn);
	},
	error: function(msg, fn) {
		this.alert(msg, 'error', fn);
	},
	confirm: function(msg, fn1, fn2) {
		swal({
            title: msg,
            type: 'warning',
            showCancelButton: true,
            cancelButtonText: '取消',
            confirmButtonColor: '#DD6B55',
            confirmButtonText: '确定',
            closeOnConfirm: false
        }, function(isConfirm){   
        	if (isConfirm) {     
        		if (fn1 && fn1 instanceof Function) {
        			fn1();
        		}
        	} else {     
        		if (fn2 && fn2 instanceof Function) {
        			fn2();
        		}
        	} 
        });
	}
};

			  
