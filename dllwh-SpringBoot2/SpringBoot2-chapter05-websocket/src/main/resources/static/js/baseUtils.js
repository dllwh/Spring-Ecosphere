/**
 * 时间格式化
 */
function formatDate(now) {
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var date = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var second = now.getSeconds();
	return year + "-" + (month = month < 10 ? ("0" + month) : month) + "-" + (date = date < 10 ? ("0" + date) : date)
			+ " " + (hour = hour < 10 ? ("0" + hour) : hour) + ":" + (minute = minute < 10 ? ("0" + minute) : minute)
			+ ":" + (second = second < 10 ? ("0" + second) : second);
}

/**
 * 判断是否为空.
 * 
 * @param data
 *            基本类型的变量
 * @returns boolean
 */
function isBlank(data) {
	return (data == undefined || data == null || data == '') ? true : false;
}

/**
 * 判断是否不为空.
 * 
 * @param data
 *            基本类型的变量
 * @returns boolean
 */
function isNotBlank(data) {
	return (data != undefined && data != null && data != '') ? true : false;
}

/**
 * 类型判断
 */
var Types = (function() {
	var types = {};
	types.get = function(val) {
		var typeStrig = toString.call(val);
		return typeStrig.slice(8, typeStrig.length - 1);
	}

	types.is = function(val, type) {
		return types.get(val) === type;
	}

	types.isString = function(val) {
		return types.is(val, 'String');
	}

	types.isNull = function(val) {
		return types.is(val, 'Null');
	}

	types.isNumber = function(val) {
		return types.is(val, 'Number');
	}

	types.isBoolean = function(val) {
		return types.is(val, 'Boolean');
	}

	types.isUndefined = function(val) {
		return types.is(val, 'Undefined');
	}

	types.isArray = function(val) {
		return types.is(val, 'Array');
	}

	types.isObject = function(val) {
		return types.is(val, 'Object');
	}

	types.isRegExp = function(val) {
		return types.is(val, 'RegExp');
	}

	types.isFunction = function(val) {
		return types.is(val, 'Function');
	}

	types.isWindow = function(val) {
		return types.is(val, 'Window');
	}

	types.isArguments = function(val) {
		return types.is(val, 'Arguments');
	}

	return types;
})();