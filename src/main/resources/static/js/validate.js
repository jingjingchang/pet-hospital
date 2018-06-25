/**
 * UPA validate
 */
if (typeof(WX) == 'undefined') {
    WX = {};
}
WX.validate = {
	init : function(opt) {
		var options = $.extend({}, WX.validate.defaults, opt);
		$(options.selector).validate({
            errorElement : 'span',  
            errorClass : options.errorClass,  
            focusInvalid : options.focusInvalid, 
            rules : options.rules,  
            messages : options.messages,
            highlight : function(element) {
                $(element).closest('.form-group').addClass('has-error');  
            },  
            success : function(label) {  
                label.closest('.form-group').removeClass('has-error');  
                label.remove();  
            },  
            errorPlacement : function(error, element) {  
            	var parent = element.parent('div');
            	var cls = parent[0].className;
            	if (cls.indexOf('input-group') == -1) {
            		parent.append(error);
            	} else {
            		parent.parent('div').append(error); 
            	}
            },  
            submitHandler : function(form) {
                form.submit();  
            }  
        });  
		
		/*
        $('.form-inline input').keypress(function(e) {
            if (e.which == 13) {  
                if ($('.form-inline').validate().form()) {  
                    $('.form-inline').submit();  
                }  
                return false;  
            }
        }); 
        */
	}
}

WX.validate.defaults = {
	selector: '#ff',
	errorClass: 'help-block',
	focusInvalid: false,
    ignore: ":hidden",
	rules: {},
	messages: {}
}

jQuery.extend(jQuery.validator.messages, {
    required: "必选字段",  
	remote: "请修正该字段",  
	email: "请输入正确格式的电子邮件",  
	url: "请输入合法的网址",  
	date: "请输入合法的日期",  
	dateTime: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",  
	number: "请输入合法的数字",  
	digits: "只能输入整数",  
	creditcard: "请输入合法的信用卡号",  
	equalTo: "请再次输入相同的值",  
	accept: "请输入拥有合法后缀名的字符串",  
	maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),  
	minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),  
	rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),  
	range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),  
	max: jQuery.validator.format("请输入一个最大为 {0} 的值"),  
	min: jQuery.validator.format("请输入一个最小为 {0} 的值")  
});  

//jquery validator 自定义类型
jQuery(function(){     
    jQuery.validator.methods.dateTime = function(value, element) {
    	return this.optional(element) || /^\d{4}-(?:0\d|1[0-2])-(?:[0-2]\d|3[01])( (?:[01]\d|2[0-3])\:[0-5]\d\:[0-5]\d)?$/.test(value);
    }; 
}); 