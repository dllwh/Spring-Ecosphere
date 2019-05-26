/**
 * 扩展javsScript工具函数
 */
var dllwh = dllwh || {};
dllwh.data = dllwh.data || {};// 用于存放临时的数据或者对象

// 工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};
T.p = url;

// 全局配置
$.ajaxSetup({
	dataType : "json",
	contentType : "application/json",
	cache : false,
	complete : function(XMLHttpRequest, textStatus) {
		// 通过XMLHttpRequest取得响应头，sessionstatus，
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
		if (sessionstatus == "timeout") {
			// 如果超时就处理 ，指定要跳转的页面(比如登陆页)
			top.layer.open({
				title : '系统提示',
				area : '338px',
				icon : 3,
				move : false,
				anim : -1,
				isOutAnim : false,
				content : '注：长时间未操作,请稍后重新登录.',
				btn : [ '立即退出' ],
				btnAlign : 'c'
			});
		} else if (textStatus == "timeout") {
			dialogAlert("请求超时，请稍候重试...", "error");
		} else if (textStatus == "error") {
			dialogAlert("错误请求，请稍候重试...", "error");
		} else if (textStatus == "notmodified") {
			dialogAlert("错误请求，无法完成此操作", "error");
		} else if (textStatus == "parsererror") {
			dialogAlert("网络问题，请稍候重试...", "error");
		}
		if (typeof (callbackFunHandler) === "function") {
			callbackFunHandler();
		}
	}
});

/**
 * 判断是否为空 不是一个空对象，或者未定义，或者不等于空字符串
 */
dllwh.isNullOrEmpty = function(obj) {
	if ((typeof (obj) == "string" && (obj == ""|| obj == "null")) || obj == null
			|| obj == undefined) {
		return false;
	} else {
		return true;
	}
}

// 重写alert
window.alert = function(msg, callback) {
	parent.layer.alert(msg, function(index) {
		parent.layer.close(index);
		if (typeof (callback) === "function") {
			callback("ok");
		}
	});
}

// 重写confirm式样框
window.confirm = function(msg, callback) {
	parent.layer.confirm(msg, {
		btn : [ '确定', '取消' ]
	}, function() {// 确定事件
		if (typeof (callback) === "function") {
			callback("ok");
		}
	});
}

// 时间设置
dllwh.currentTime = function() {
	var d = new Date(), str = '';
	str += d.getFullYear() + '年';
	str += d.getMonth() + 1 + '月';
	str += d.getDate() + '日';
	str += d.getHours() + '时';
	str += d.getMinutes() + '分';
	str += d.getSeconds() + '秒';
	return str;
}

/** 毫秒数转日期格式 */
dllwh.genStrDateTime = function(data) {
	if (data == null) {
		return "";
	} else {
		var c = new Date();
		c.setTime(data);
		var mon = c.getMonth() + 1;
		if (mon < 10) {
			mon = "0" + mon;
		}
		var dat = c.getDate();
		if (dat < 10) {
			dat = "0" + dat;
		}

		var hours = c.getHours();
		if (hours < 10) {
			hours = "0" + hours;
		}
		var minutes = c.getMinutes();
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		var seconds = c.getSeconds();
		if (seconds < 10) {
			seconds = "0" + seconds;
		}
		return c.getFullYear() + "-" + mon + "-" + dat + " " + hours + ":"
				+ minutes + ":" + seconds;
	}
}