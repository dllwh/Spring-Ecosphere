/**
 * 扩展javsScript工具函数
 */

var dllwh = dllwh || {};
dllwh.data = dllwh.data || {};// 用于存放临时的数据或者对象

/**
 * 回调函数 
 */
var callbackFunHandler = function(){
	
}
/**
 * 全局配置
 */
$.ajaxSetup({
	dataType: "json",
	cache: false,
	complete:function(XMLHttpRequest,textStatus){
		// 通过XMLHttpRequest取得响应头，sessionstatus， 
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); 
		if (sessionstatus == "timeout") { 
			// 如果超时就处理 ，指定要跳转的页面(比如登陆页)
			alert("长时间未操作,请稍后重新登录");
		}else if(textStatus=="timeout"){
			alert("请求超时，请稍候重试...");
		} else if(textStatus=="error"){
			alert("错误请求，请稍候重试...");
		} else if(textStatus=="notmodified"){
			alert("错误请求，无法完成此操作");
		} else if(textStatus=="parsererror"){
			alert("网络问题，请稍候重试...");
		}
		callbackFunHandler();
	}
});


/**
 * js获取项目根路径
 */
dllwh.getRootPath = function() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	if (webName == "") {
		return window.location.protocol + '//' + window.location.host + '/';
	}
	else {
		return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
	}
}

/**
 * 判断是否为空 不是一个空对象，或者未定义，或者不等于空字符串
 */
dllwh.isNullOrEmpty = function(obj) {
	if ((typeof (obj) == "string" && obj == "") || obj == null || obj == ''
			|| typeof (obj) == undefined) {
		return true;
	} else {
		return false;
	}
}

/** 是否存在指定函数  */
dllwh.isExitsFunction = function (funcName){
	try {
		if (typeof(eval(funcName)) == "function") {
			return true;
		}
	} catch (error) {
		
	}
	return false;
}

/**
 * 判断是否登录，没登录刷新当前页，促使Shiro拦截后跳转登录页
 * @param result	ajax请求返回的值
 * @returns {如果没登录、登录失效，刷新当前页}
 */
dllwh.isLogin = function(result) {
	if (result && result.code && (result.code == '201' || result.code == '201')) {
		window.location.reload(true);// 刷新当前页
	}
	return true;// 返回true
}

/** 是否存在指定变量 */
dllwh.isExitsVariable = function (variableName){
	try {
		if (typeof(variableName) == "undefined") {
			// alert("value is undefined"); 
			return false;
		} else {
			// alert("value is true"); 
			return true;
		}
	} catch (error) {
		
	}
	return false;
}