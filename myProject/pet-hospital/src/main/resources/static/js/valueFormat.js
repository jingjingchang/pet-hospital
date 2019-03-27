/**
 * DM grid format
 */

if (typeof(DM) == 'undefined') {
    DM = {};
}
DM.format = {
	/**
	 * 格式化布尔类型，返回是、否
	 * @param value
	 * @param type
	 * @param row
	 * @returns
	 */
	formatBoolean: function(value, type, row) {
		return value ? DM.format.defaults.yes : DM.format.defaults.no;
	},
	formatNullToLine: function(value, type, row) {
		return value ? value : '-';
	},
	formatDate: function(value, type, row) {
		if (!value) {
			return '';
		}
		var d = new Date(value);
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		var day = d.getDate();
		return year + "-" + DM.format.formatNum(month) + "-" + DM.format.formatNum(day);
	},
	formatDateTime: function(value, type, row) {
		if (!value) {
			return '';
		}
		var d = new Date(value);
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		var day = d.getDate();

		var hours = d.getHours();
		var minutes = d.getMinutes();
		var seconds = d.getSeconds();

		return year + "-" + DM.format.formatNum(month) + "-" + DM.format.formatNum(day)  + " " + DM.format.formatNum(hours) + ":" + DM.format.formatNum(minutes) + ":"
				+ DM.format.formatNum(seconds);
	},
	formatNum: function(num) {
		return num < 10 ? '0' + num : num;
	},
	formatFileSize: function(val, type, row) {
		var size = val;
		var unit = 'Bytes';
		if (size > 10000) {
			size = Math.ceil(size / 1000);
			unit = 'KB';
			if (size > 10000) {
				size = Math.ceil(size / 1000);
				unit = 'MB';
				if (size > 10000) {
					size = Math.ceil(size / 1000);
					unit = 'GB';
				}
			}
		}
		return size + unit;
	},
	/**
	 * 将数值四舍五入(保留2位小数)后格式化成金额形式
	 *
	 * @param num 数值(Number或者String)
	 * @return 金额格式的字符串,如'1,234,567.45'
	 * @type String
	 */
	formatCurrency: function(num) {
		if (!num) {
			return '';
		}
	    num = num.toString().replace(/\$|\,/g, '');
	    if (isNaN(num)) {
	    	num = "0";
	    }
	    var sign = (num == (num = Math.abs(num)));
	    num = Math.floor(num*100 + 0.50000000001);
	    var cents = num%100;
	    num = Math.floor(num/100).toString();
	    if (cents < 10) {
	    	cents = "0" + cents;
	    }
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++) {
	    	num = num.substring(0, num.length-(4*i + 3)) + ',' + num.substring(num.length-(4*i+3));
	    }
	    return (((sign)?'':'-') + num + '.' + cents);
	},
	formatDateDesc: function(val, type, row) {
		if (!val) {
			return '';
		}
		var start = new Date(val);
		var end = new Date();
		var inter = end.getTime() - start.getTime();
		// 计算相隔天数
		var days = parseInt(inter/(1000 * 3600 * 24));
		if(days > 0){
			return days + "天前";
		}
		// 计算小时
		var hours = parseInt(inter/(1000 * 3600));
		if(hours > 0){
			return hours + "小时前";
		}
		// 计算分钟
		var minutes = parseInt(inter/(1000 * 60));
		if(minutes > 0){
			return minutes + "分钟前";
		}
		// 计算毫秒
		var seconds = parseInt(inter/1000);
		if(seconds > 0){
			return "刚刚";
		}
		return '';
	},
	formatDateTimeDisplay: function(val, type, row) {
		if (!val || val == '') return '';
		var time = DM.format.formatDateTime(val);
		var date = DM.format.formatDate(val);
		var html = '<span class="visible-md-inline visible-lg-inline">'+time+'</span>';
		html += '<span class="hidden-md hidden-lg" title="'+time+'">'+date+'</span>';
		return html;
	},
    getNow: function() {
        var date = new Date();
        var Y = date.getFullYear();
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        var selectDate = Y + '-' + M + ( parseInt(D) < 10 ? '0' + D : D) + '';
        return (selectDate);
    },
    getNowMouth: function() {
        var date = new Date();
        var Y = date.getFullYear();
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
        var selectDate = Y + '-' + M;
        return (selectDate);
    }
}

DM.format.defaults = {
	yes: '是',
	no: '否'
}
