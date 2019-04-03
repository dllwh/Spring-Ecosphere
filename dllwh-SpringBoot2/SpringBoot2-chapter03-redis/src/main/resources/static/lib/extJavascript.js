/**
 * 扩展javsScript工具函数
 */
var dllwh = dllwh || {};
dllwh.data = dllwh.data || {};// 用于存放临时的数据或者对象

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