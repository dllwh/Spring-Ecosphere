/**
 * 
 */

var websocketService = {};

/**
 * 获取连接服务器信息
 */
websocketService.serverInfo = function() {

}

/**
 * 获取连接服务器时间
 */
websocketService.getCurrentTime = function() {
	var currentTime;
	$.ajax({
		url : "getCurrentTime",
		async : false,
		xhrFields : {
			withCredentials : true
		},
		success : function(result) {
			currentTime = result;
		},
		error : function(e) {
			currentTime = new Date().getTime();
		}
	});
	return currentTime;
}

/**
 * 获取连接服务器连接密钥
 */
websocketService.getWebSocketKey = function() {
	var time = new Date().getTime();
	var webSocketKey;
	$.ajax({
		url : "getWebSocketKey",
		async : false,
		data : {
			time : time
		},
		xhrFields : {
			withCredentials : true
		},
		success : function(result) {
			if (result.resultCode == 200) {
				webSocketKey = result.data;
			}
		},
		error : function(e) {
		}
	})
	return webSocketKey;
}