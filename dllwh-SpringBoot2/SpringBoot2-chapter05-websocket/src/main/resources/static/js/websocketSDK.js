var websocketUtils = function() {
	
}

var WebsocketState = {};
var webSocket;
var ws = new websocketUtils();
var websocketClient = new websocketUtils();

WebsocketState.CONNECTING = 0;
WebsocketState.OPEN = 1;
WebsocketState.LOGINING = 2;
WebsocketState.LOGIN_SUCCESS = 3;
WebsocketState.LOGOUT = 4;
WebsocketState.CLOSE = 5;

// 监听窗口关闭事件，当窗口关闭时，主动去关闭webSocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
	websocketUtils.closeWebSocket();
}

/**
 * 默认配置
 */
var websocketConfig = {
	protocol : 'ws://',
	server : 'localhost',
	port : 8205,
	policyPort : 843,
	isBrowser : typeof window === 'object' && window.window === window,
	version : '1.0.0',
	// 连接标识,第一次连接成功置为true,主动断开置为false,为true时，因网络原因断开时自动重连
	isConnected : false,
	// 登录状态
	isLogin : false,
	// 是否发送心跳包
	isSendHeartBeat : false,
	// 心跳发送时间间隔
	heartBeatInterval : 3000,
	// 是否开启断网自动重连，避免不间断的重连操作
	isReconnect : false,
	// 被动断开连接时，重连时间间隔
	reconnectInterval : 3000,
	// 最大重连次数（-1为无限制）
	maxReconnectCount : -1,
	// 重连成功后，是否重新发送之前发送失败的消息
	isReconnectedResend : false,
	// 断开连接后，保留失败消息的条数
	unReconnectMsgCount : 10,
	// 间隔时间内未接收到服务端心跳，断开连接
	unliveTime : 10000
};

if (websocketConfig.isBrowser) {
	if (location.protocol == 'https:') {
		websocketConfig.protocol = 'wss://';
		websocketConfig.server = 'localhost';
		websocketConfig.port = 443;
	}
}

/**
 * 判断是否 支持 WebSocket
 */
websocketUtils.prototype.checkBrowser = function() {
	window.WebSocket = window.WebSocket || window.MozWebSocket;
	if (window.WebSocket) {
		return true;
	} else {
		return false;
	}
}

/**
 * 检验 WebSocket
 */
websocketUtils.prototype.checkWebsocket = function() {
	if (typeof webSocket === undefined || typeof webSocket == null || typeof webSocket === "undefined") {
		console.log("Websocket还没有连接或者连接失败，请进行检测");
		return false;
	}
	if (webSocket.readyState == 3) {
		console.log("Websocket已经关闭，请重新连接");
		return false;
	}
	return true;
}

/**
 * 初始化连接参数
 */
websocketUtils.prototype.init = function(option) {
	websocketConfig.appId = option['appId'];
	websocketConfig.roomId = option['roomId'];
	websocketConfig.userName = option['userName'];
	websocketConfig.nickName = option['nickName'];
	websocketConfig.role = option['role'];
	websocketConfig.time = option['time'];
	websocketConfig.key = option['key'];
	this.openConnection();
}

/**
 * 
 * @param option
 * @returns
 */
websocketUtils.prototype.setCallback = function(option){
	
}

websocketUtils.prototype.initSocket = function() {
	readyState = WebsocketState.CONNECTING;
	// WebSocket客户端 PS：URL开头表示WebSocket协议 中间是域名端口 结尾是服务端映射地址
	if(window.WEB_SOCKET_FORCE_FLASH){
		webSocket = new WebSocket(websocketConfig.protocol + websocketConfig.server + ":" + websocketConfig.port , websocketConfig.policyPort + "/websocket/duleilewuhen");
	} else {
		webSocket = new WebSocket(websocketConfig.protocol + websocketConfig.server + ":" + websocketConfig.port + "/websocket/duleilewuhen");
	}
	console.debug("window.WEB_SOCKET_FORCE_FLASH:"+window.WEB_SOCKET_FORCE_FLASH);
	
	webSocket.onopen = function(event) { // 连接建立时触发
		readyState = WebsocketState.OPEN;
		websocketClient.onSocketOpen(event);
	};
	webSocket.onclose = function(event) { // 连接关闭时触发
		if (webSocket != null) {
			webSocket = null; // 清理
		}
		readyState = WebsocketState.CLOSE;
		websocketClient.onSocketClose(event);
	};
	webSocket.onmessage = function(event) { // 客户端接收服务端数据时触发
		// 发现消息进入， 开始处理前端触发逻辑
		websocketClient.onSocketMessage(event);
	};
	webSocket.onerror = function(event) { // 通信发生错误时触发
		websocketClient.onSocketError(event);
	};
	webSocket.onlog = function(msg) {
		var err ={code:500};
		err['msg'] = "websocket err";
		if(event.code){
			err['code'] = event.code;
		}
		if(event.msg){
			err['msg'] = event.msg;
		}
		websocketClient.onSocketError(err,event);
	};
}
/**
 * 连接方法
 */
websocketUtils.prototype.openConnection = function() {
	if(this.readyState == WebsocketState.OPEN || this.readyState == WebsocketState.LOGOUT){
		this.userLogin();
		return;
	}
	this.initSocket();
}

/**
 * 自动连接
 */
websocketUtils.prototype.autoReconnect = function() {
	var reconnectCount = 0;
	var task = function() {
		// 如果主动断开连接，则停止重连
		if (!isConnected) {
			console.log('主动断开连接，放弃重连!');
			return;
		}
		// 如果重连成功，则停止重连
		if (isOpen) {
			console.log('连接成功，停止重连!');
			return;
		}

		reconnectCount++;
		// 如果重连次数超过最大重连次数，则停止重连
		if (maxReconnectCount != -1 && maxReconnectCount < reconnectCount) {
			console.log('重连次数达到' + reconnectCount + '次,停止重连!');
		}
		console.log('第' + reconnectCount + '次尝试重连');
		var lastReconnectTime = new Date().getTime();
		reConnection();
	}

	setTimeout(task, reconnectInterval);
}

/**
 * 重新连接
 */
websocketUtils.prototype.reConnection = function() {

}

/**
 * 获取连接服务器信息
 */
websocketUtils.prototype.serverInfo = function() {
	return '{"code":"200","msg":"success","list":[{"addr":"ip地址或者域名","port":"端口","policyport":"代理端口","weight":1}]}';
}

/**
 * 获取连接服务器连接密钥
 */
websocketUtils.prototype.getWebSocketKey = function() {
	return '';
}

/**
 * 自定义Ws连接函数：服务器连接成功
 */
websocketUtils.prototype.onSocketOpen = function(event) {
	console.log('连接成功，现在你可以发送信息进行测试了！');
	isConnected = true;
	onOpen(event);
}

/**
 * 自定义Ws关闭事件：Ws连接关闭后触发
 */
websocketUtils.prototype.onSocketClose = function(event) {
	isConnected = false;
	if (webSocket) {
		onClose(event);
		console.log('========== WebSocket关闭连接 ===========');
	} else {
		console.log('Websocket未连接，无需进行断开操作！');
	}
}

/**
 * 自定义Ws异常事件：Ws报错后触发
 */
websocketUtils.prototype.onSocketError = function(err,event) {
	console.log('WebSocket发生异常');
	onError(event);
}

/**
 * 自定义Ws异常事件：
 */
websocketUtils.prototype.onlog = function(msg) {

}

/**
 * 自定义Ws消息接收函数：服务器向前端推送消息时触发 处理各种推送消息
 */
websocketUtils.prototype.onSocketMessage = function(event) {
	onMessage(event);
	console.log('WebSocket收到消息：%c' + event.data, 'color:green');
	// 登录响应指令
	// 当前用户连接断开通知
	// 群组消息
	// 收到消息后，将消息发送给页面
	// 对收到的消息进行排序后转换成json对象
	var resutMsg = "";
	try{
		resutMsg = JSON.parse(event);
	}catch(e){
		var err ={code:300};
		err['msg'] = "parse msg to json err,msg:"+event;
		this.onSocketError(err,e);
		return;
	}
	
	for (var i = 0; i < resutMsg.length; i++) {
		var msg = resutMsg[i];
		var cmd = msg.cmd;
		switch(cmd){
			case 'login':{ // 登录响应指令
				if(msg.code==1) {
					readyState = WebsocketState.LOGIN_SUCCESS;
				} else {
					readyState = WebsocketState.OPEN;
				}
				this.login(msg);
				break;
			}
			case 'logout':{ // 当前用户连接断开通知
				readyState = WebsocketState.LOGOUT;
				this.logout(msg);
				break;
			}
			default:{
				this.on_msg(msg);
				break;
			}
		}
	}
}

/**
 * 发送指令
 */
websocketUtils.prototype.sendSocketMessage = function(obj){
	if(this.readyState == WebsocketState.CLOSE){
		this.connect();
		return;
	}else if(this.readyState == WebsocketState.OPEN || this.readyState == WebsocketState.LOGOUT){
		this.userLogin();
		return;
	}
	webSocket.send(obj);
}

/**
 * 发送登录事件
 */
websocketUtils.prototype.userLogin = function(msg) {
	if(this.readyState == WebsocketState.LOGIN_SUCCESS){
		return;
	}
	if(this.readyState == WebsocketState.CLOSE){
		this.openConnection();
		return;
	}
	this.readyState = WebsocketState.LOGINING;
	var loginCMD = {cmd:'login'};
	loginCMD["appid"] = this.appid;
	loginCMD["roomid"] = this.roomid;
	loginCMD["username"] = this.username;
	loginCMD["nickname"] = this.nickname;
	loginCMD["role"] = parseInt(this.role);
	loginCMD["time"] = parseInt(this.time);
	loginCMD["key"] = this.key;
	loginCMD["terminal"] = JSON.parse("{\"client_type\":0,\"client_version\":\"" + websocketConfig.version + "\","+getClientInfo()+"}");
	webSocket.send(JSON.stringify(loginCMD));
}

/**
 * 重新登录
 */
websocketUtils.prototype.reLogin = function(param) {
	console.log("重新登录");
	this.time = param['time'];
	this.key = param['key'];
	if(!this.checkLoginParam()){
		return;
	}
	
	if(this.readyState == WebsocketState.CLOSE){
		this.connect();
	}else if(this.readyState == WebsocketState.OPEN || this.readyState == WebsocketState.LOGOUT){
		this.login();
	}
}

/**
 * 连接断开
 */
websocketUtils.prototype.logout = function(msg) {

}

/**
 * 检查参数
 */
websocketUtils.prototype.checkLoginParam = function() {
	var err ={code:404};
	if(isBlank(this.appid)){
		err['msg'] = "empty appid";
	}
	if(isBlank(this.roomid)){
		err['msg'] = "empty roomid";
	}
	if(isBlank(this.username)){
		err['msg'] = "empty username";
	}
	if(isBlank(this.nickname)){
		err['msg'] = "empty nickname";
	}
	if(isBlank(this.role)){
		err['msg'] = "empty role";
	}
	if(isBlank(this.time)){
		err['msg'] = "empty time";
	}
	if(isBlank(this.key)){
		err['msg'] = "empty key";
	}
	if(err['msg']){
		this.onSocketError(err,null);
		return false;
	}
	return true;
}

/**
 * 系统通知只能由服务端发送，主要返回用户进入、离开房间的通知，并返回当前在线用户。
 */
websocketUtils.prototype.handleAction = function(cmd) {
	// 登录成功
	if ("onLoginSuccess" === cmd) {
		if (typeof loginSuccessCallBack === "function") {
			loginSuccessCallBack();
		}
	}

	// 登录出现错误时的回调
	if ("onLoginError" === cmd) {
		if (typeof loginErrorCallBack === "function") {
			loginErrorCallBack({
				errorCode : 1,
				msg : "request error"
			}); // 至于错误原因暂时不考虑，后续会加上
		}
	}
	
	// 心跳包响应
	if ("pong" === cmd) {
		
	}
	
	// 用户进入时的回调
	if ("onUserEnter" === cmd) {
		if (typeof userEnterCallBack === "function") {
			userEnterCallBack();
		}
	}
	
	// 用户离开的回调
	if ("onUserDisconnect" === cmd) {
		if (typeof userEnterCallBack === "function") {
			UserDisconnectCallBack();
		}
	}
}

/**
 * 发送心跳包
 */
var sendHeartBeat = {
	// 心跳对象内timeout默认为每3秒发一次心跳，可以通过heartBeatInterval自定义该值
	// timeoutObj、serverTimeoutObj是清除定时器用的对象，
	timeout: websocketUtils.heartBeatInterval, 
	timeoutObj: null,
	serverTimeoutObj: null,
	reset: function () {
		clearTimeout(this.timeoutObj);
		clearTimeout(this.serverTimeoutObj);
		return this;
	},
	start: function () {
		this.timeoutObj = setTimeout(()=> {
			var cmd = {
				cmd:'ping'
			};
		},this.timeout);
		this.serverTimeoutObj = setTimeout(() =>{
			
		},this.timeout);
	}
}

/**
 * 消息发送
 */
websocketUtils.prototype.publicChatMessage = function(msg, type) {
	var content_obj = {};
	content_obj.type = type;
	content_obj.msg = msg;
	this.publicGroupMsg("chat",JSON.stringify(content_obj));
}

/**
 * 失败消息重发
 */
websocketUtils.prototype.failedMsgResend = function(obj) {

}

/**
 * 发送群组消息
 */
websocketUtils.prototype.publicGroupMsg = function(cmd, content) {
	var msg_obj = {};
	msg_obj.cmd = cmd;
	msg_obj.content = content;
	var msg = JSON.stringify(msg_obj);
	if(this.readyState == WebsocketState.LOGIN_SUCCESS) {
		this.send(msg);
	} else {
		this.addTask(function (){
			this.send(msg)
		});
	}
}

/**
 * 断开连接
 */
websocketUtils.prototype.closeWebSocket = function(data) {
	websocketConfig.isLogin = false;
	websocketConfig.isConnected = false;
	if(webSocket){
		webSocket.close();
	}
}

websocketUtils.prototype.addTask = function(task) {
	if (this.tasks.length<100) {
		this.tasks.push(task);
	}
};

websocketUtils.prototype.debug = function(msg){
	this.onLog({
		type : "log",
		data : msg,
		bubbles : false,
		cancelable : false
	});
},

/**
 * 当前在线列表
 */
websocketUtils.prototype.onlineUserList = function(data) {

}

/**
 * 在线人数
 */
websocketUtils.prototype.onUserCountMessage = function(data) {
	console.log("当前在线人数[onUserCountMessage]：", data);
}

/**
 * 系统消息(广播消息回调)
 */
websocketUtils.prototype.onBroadcastMsg = function(data) {
	console.log('系统消息：' + data.content);
}

/**
 * 接收私聊
 */
websocketUtils.prototype.onPrivateChatMessage = function(data) {

}

/**
 * 接收公聊
 */
websocketUtils.prototype.onPublicChatMessage = function(data) {

}

/**
 * 接收私聊回复
 */
websocketUtils.prototype.onPrivateAnswer = function(data) {

}

/**
 * 页面布局配置
 */
websocketUtils.prototype.onRoomSetting = function(data) {

}

/**
 * 已经在聊天的列表信息
 */
websocketUtils.prototype.onLiveInteractionChatusers = function() {

}
