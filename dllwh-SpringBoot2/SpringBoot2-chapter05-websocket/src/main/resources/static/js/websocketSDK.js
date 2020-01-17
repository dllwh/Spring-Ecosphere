var websocketUtils = function() {
	
}

var websocketState = {};
var webSocket;
var ws = new websocketUtils();
var websocketClient = new websocketUtils();
var websocketService = {};
var serverList={};

// 表示连接尚未建立。
websocketState.CONNECTING = 0;
// 表示连接已建立，可以进行通信。
websocketState.OPEN = 1;
// 表示连接正在进行关闭。
websocketState.LOGINING = 2;
// 表示连接登录成功
websocketState.LOGIN_SUCCESS = 3;
// 表示连接已经退出
websocketState.LOGOUT = 4;
// 表示连接已经关闭或者连接不能打开
websocketState.CLOSE = 5;

/**
 * 监听窗口关闭事件，当窗口关闭时，主动去关闭webSocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
 */
window.onbeforeunload = function () {
	console.log("主动去关闭webSocket连接");
}
window.onunload =function() {
	console.log("主动去关闭webSocket连接");
}

/**
 * 获取连接服务器信息
 */
websocketService.serverInfo = function() {
	$.ajax({
		url: "serverInfo",
		xhrFields: {
			withCredentials: true
		},
		success: function (result) {
			if(result.code == 200){
				console.log(result);
			}
		},
		error: function (e) {
		}
	})
}

/**
 * 获取连接服务器时间
 */
websocketService.getCurrentTime = function() {
	var currentTime;
	$.ajax({
		url: "getCurrentTime",
		async: false,
		xhrFields: {
			withCredentials: true
		},
		success: function (result) {
			currentTime = result;
		},
		error: function (e) {
			currentTime = new Data().getTime();
		}
	});
	return currentTime;
}

/**
 * 获取连接服务器连接密钥
 */
websocketService.getWebSocketKey = function() {
	$.ajax({
		url: "getWebSocketKey",
		xhrFields: {
			withCredentials: true
		},
		success: function (result) {
			if(result.code == 200){
				console.log(result);
			}
		},
		error: function (e) {
		}
	})
}

/**
 * 默认配置
 */
var websocketConfig = {
	protocol : 'ws://',
	policyPort : 843,
	isBrowser : typeof window === 'object' && window.window === window,
	version : '1.0.0',
	// 连接标识,第一次连接成功置为true,主动断开置为false,为true时，因网络原因断开时自动重连
	isConnected : false,
	// 是否自动登录
	isAutoLogin: false,
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
	}
}

/**
 * 判断当前浏览器是否支持WebSocket
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
	if (typeof webSocket == undefined || typeof webSocket == null || typeof webSocket === "undefined" || webSocket == null) {
		console.log("Websocket还没有连接或者连接失败，请进行检测");
		return false;
	}
	if (webSocket.readyState == 3) {
		console.log("Websocket已经关闭，请重新连接");
		return false;
	}
	if (webSocket.readyState == 0) {
		console.log("Websocket尚未建立连接，请重新连接");
		return false;
	}
	return true;
}

/**
 * 初始化连接参数
 */
websocketUtils.prototype.init = function(option) {
	websocketConfig.server = 'localhost',
	websocketConfig.port = 8205,
	
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
	readyState = websocketState.CONNECTING;
	// WebSocket客户端 PS：URL开头表示WebSocket协议 中间是域名端口 结尾是服务端映射地址
	if(window.WEB_SOCKET_FORCE_FLASH){
		webSocket = new WebSocket(websocketConfig.protocol + websocketConfig.server + ":" + websocketConfig.port , websocketConfig.policyPort + "/websocket/duleilewuhen");
	} else {
		webSocket = new WebSocket(websocketConfig.protocol + websocketConfig.server + ":" + websocketConfig.port + "/websocket/duleilewuhen");
	}
	console.debug("window.WEB_SOCKET_FORCE_FLASH:"+window.WEB_SOCKET_FORCE_FLASH);
	
	webSocket.onopen = function(event) { // 连接建立时触发
		readyState = websocketState.OPEN;
		websocketClient.onSocketOpen(event);
	};
	webSocket.onclose = function(event) { // 连接关闭时触发
		if (webSocket != null) {
			webSocket = null; // 清理
		}
		readyState = websocketState.CLOSE;
		websocketClient.onSocketClose(event);
	};
	webSocket.onmessage = function(event) { // 当服务端给客户端发来消息的时候触发
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
	if(this.checkWebsocket()){
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
 * 获取连接服务器连接密钥
 */
websocketUtils.prototype.getlineUserList = function() {

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
	onError(err);
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
	heartCheck.reset().start();      // 拿到任何消息都说明当前连接是正常的
	
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
					readyState = websocketState.LOGIN_SUCCESS;
				} else {
					readyState = websocketState.OPEN;
				}
				this.login(msg);
				break;
			}
			case 'logout':{ // 当前用户连接断开通知
				readyState = websocketState.LOGOUT;
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
	if(this.readyState == websocketState.CLOSE){
		this.connect();
		return;
	}else if(this.readyState == websocketState.OPEN || this.readyState == websocketState.LOGOUT){
		this.userLogin();
		return;
	}
	webSocket.send(obj);
}

/**
 * 发送登录事件
 */
websocketUtils.prototype.userLogin = function(option) {
	if(!ws.checkWebsocket()){
		ws.init(option);
	}
	if(webSocket.readyState == websocketState.LOGIN_SUCCESS){
		return;
	}
	if(webSocket.readyState == websocketState.CLOSE){
		this.openConnection();
		return;
	}
	if(!this.checkLoginParam(option)){
		return;
	}
	this.readyState = websocketState.LOGINING;
	var loginCMD = {cmd:'login'};
	loginCMD["appId"] = option.appId;
	loginCMD["roomId"] = option.roomId;
	loginCMD["userName"] = option.userName;
	loginCMD["nickName"] = option.nickName;
	loginCMD["role"] = parseInt(option.role);
	loginCMD["time"] = parseInt(option.time);
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
	
	if(this.readyState == websocketState.CLOSE){
		this.connect();
	}else if(this.readyState == websocketState.OPEN || this.readyState == websocketState.LOGOUT){
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
websocketUtils.prototype.checkLoginParam = function(option) {
	var err ={code:404};
	if(isBlank(option.appId)){
		err['msg'] = "empty appid";
	}
	if(isBlank(option.roomid)){
		err['msg'] = "empty roomid";
	}
	if(isBlank(option.username)){
		err['msg'] = "empty username";
	}
	if(isBlank(option.nickname)){
		err['msg'] = "empty nickname";
	}
	if(isBlank(option.role)){
		err['msg'] = "empty role";
	}
	if(isBlank(option.time)){
		err['msg'] = "empty time";
	}
	if(isBlank(option.key)){
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
var heartCheck = {
	// 心跳对象内timeout默认为每3秒发一次心跳，可以通过heartBeatInterval自定义该值
	// timeoutObj、serverTimeoutObj是清除定时器用的对象，
	timeout: websocketUtils.heartBeatInterval, 
	timeoutObj: null,
	serverTimeoutObj: null,
	cmd:"heartBeat"
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
 * 消息撤回,只能撤回2分钟内的消息
 */
websocketUtils.prototype.callBackChatMessage = function(msg, type) {
	var data = {};
	if(type=='user'){
		data = {
			"type":"delMsg", 
			"cid":cid,
			"id":toId,
			"type":type
		}
	} else {
		var res =[];
		data = {
				"type":"delMsg", 
				"cid":cid,
				"id":toId,
				"type":type,
				"memberList":res
			}
	}
	ws.send(JSON.stringify(data));
}

/**
 * 发送群组消息
 */
websocketUtils.prototype.publicGroupMsg = function(cmd, content) {
	var data = {};
	data.cmd = cmd;
	data.content = content;
	var msg = JSON.stringify(data);
	if(this.readyState == WebsocketState.LOGIN_SUCCESS) {
		this.send(msg);
	} else {
		this.addTask(function (){
			this.send(msg)
		});
	}
}

/**
 * 失败消息重发
 */
websocketUtils.prototype.failedMsgResend = function(obj) {

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

/**
 * 断开登录
 */
websocketUtils.prototype.exitWebSocket = function(data) {
	websocketConfig.isLogin = false;
	websocketConfig.isConnected = true;
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
}

/**
 * 监听对方的添加或者删除好友请求，并做相应的处理
 */
websocketUtils.prototype.onPresence = function(message) {
	
	if (message.type == 'unsubscribe') { // 收到删除请求
		// 删除好友
		var removeList = {};
		removeList.cmd = 'removeList';
		removeList.type=  'friend';
		removeList.id = message.from // 好友或者群组ID
		
		//  删除历史记录
		var removeHistory = {};
		removeHistory.cmd = 'removeHistory';
		removeHistory.type=  'friend';
		removeHistory.id = message.from // 好友或者群组ID
		
		parent.location.reload();
		
	} else if(message.type =='subscribe'){// 收到添加请求
		
	} else if(message.type =='subscribed'){// 对方通过了你的好友请求
		
	} else if (message.type == 'unsubscribed') {// 拒绝好友申请
		
	} else if(message.type == 'joinGroupNotifications'){// 群管理收到加群申请,将该管理员加入消息组
		// 消息组
		var groupName = "";
		// 加入成功
		console.log('你申请加入'+groupName+'的消息已发送，请等待管理员确认');
		// 加入失败
		console.log('你申请加入'+groupName+'的消息发送失败，请刷新浏览器后重试');
		
	} else if(message.type == 'memberJoinPublicGroupSuccess'){
		
	} else if(message.type == 'joinPublicGroupSuccess'){
		
	} else if (message.type == 'addAdmin') {
		var groupName = "";
		console.log('你已成为群 <b>'+ groupName+ '</b> 的管理员，快去看看吧！');
		
	} else if (message.type == 'removeAdmin') {
		var groupName = "";
		console.log('你已被群 <b>'+ groupName + '</b> 撤消管理员。');
	}
		
}
