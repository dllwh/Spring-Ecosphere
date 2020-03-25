var websocketUtils = function() {
	
}

var webSocket;
var ws = new websocketUtils();
var websocketClient = new websocketUtils();
var serverList={};

/**
 * 默认配置
 */
var websocketConfig;

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
 * 获得焦点
 */
window.onfocus = function (){
	console.log("亲，欢迎你回来");
}
/**
 * 失去焦点
 */
window.onblur = function (){
	console.log("小伙不要走，我要和你对决");
}

/**
 * 判断当前浏览器是否支持WebSocket
 */
websocketUtils.prototype.checkBrowser = function() {
	window.WebSocket = window.WebSocket || window.MozWebSocket;
	if (window.WebSocket) {
		return true;
	} else {
		console.log("你的浏览器不支持WebSocket协议，清欢谷歌或火狐试试！！");
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
	if (webSocket.CONNECTING == webSocket.readyState) {
		console.log("Websocket已经关闭，请重新连接");
		return false;
	}
	if (webSocket.CONNECTING == webSocket.readyState) {
		console.log("Websocket尚未建立连接，请重新连接");
		return false;
	}
	if (WebSocket.OPEN == webSocket.readyState) {
		console.log("WebSocket is opend now.");
	}
	return false;
}

/**
 * 初始化连接参数
 */
websocketUtils.prototype.init = function(option) {
	websocketConfig = $.extend(webIM.config,option);
	websocketService.serverInfo();
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
	// WebSocket客户端 PS：URL开头表示WebSocket协议 中间是域名端口 结尾是服务端映射地址
	if(window.WEB_SOCKET_FORCE_FLASH){
		webSocket = new WebSocket(websocketConfig.protocol + websocketConfig.server + ":" + websocketConfig.port , websocketConfig.policyPort + "/websocket/webChat");
	} else {
		webSocket = new WebSocket(websocketConfig.protocol + websocketConfig.server + ":" + websocketConfig.port + "/websocket/webChat");
	}
	
	// 判断连接状态是否连接 0 (WebSocket.CONNECTING) 正在链接中
	if (webSocket.CONNECTING == webSocket.readyState) {
		console.log("正在连接....");
	}
	
	webSocket.onopen = function(event) { // 连接建立时触发
		if (webSocket.OPEN == webSocket.readyState) {
			console.log("WebSocket开启连接成功....");
		}
		websocketClient.onSocketOpen(event);
	};
	
	webSocket.onclose = function(event) { // 连接关闭时触发
		console.log("WebSocket关闭");
		if(null != webSocket && undefined != webSocket){
			webSocket = null; // 清理
		}
		websocketClient.onSocketClose(event);
	};
	
	webSocket.onmessage = function(event) { // 当服务端给客户端发来消息的时候触发
		// 发现消息进入， 开始处理前端触发逻辑
		websocketClient.onSocketMessage(event);
	};
	
	// 错误连接 3 (WebSocket.CLOSED) 连接已关闭或者没有链接成功
	webSocket.onerror = function(event) { // 通信发生错误时触发
		console.log('WebSocket发生异常，webSocket连接状态' + webSocket.readyState);
		if (webSocket.CLOSED == webSocket.readyState) {
			console.log("连接错误....");
			websocketClient.onSocketError(event);
		}
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
		if (!websocketConfig.isConnected) {
			console.log('主动断开连接，放弃重连!');
			return;
		}
		// 如果重连成功，则停止重连
		if (websocketConfig.isConnected) {
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
 * 获取在线用户列表
 */
websocketUtils.prototype.getlineUserList = function() {

}

/**
 * 自定义webSocket连接函数：服务器连接成功
 */
websocketUtils.prototype.onSocketOpen = function(event) {
	websocketConfig.isConnected = true;
	onOpen(event);
}

/**
 * 自定义webSocket关闭事件：webSocket连接关闭后触发
 */
websocketUtils.prototype.onSocketClose = function(event) {
	websocketConfig.isConnected = false;
	if (webSocket) {
		onClose(event);
		console.log('========== WebSocket关闭连接 ===========');
	} else {
		console.log('Websocket未连接，无需进行断开操作！');
	}
}

/**
 * 自定义webSocket异常事件：webSocket报错后触发
 */
websocketUtils.prototype.onSocketError = function(err,event) {
	onError(err);
}

/**
 * 自定义webSocket日志
 */
websocketUtils.prototype.onlog = function(msg) {

}

/**
 * 自定义webSocket消息接收函数：服务器向前端推送消息时触发 处理各种推送消息
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

	var cmd = msg.cmd;
	switch(cmd){
		case 'SYSTEM':{ // 如果是系统消息
			handleAction(cmd);
			break;
		}
		case 'CHAT':{ // 如果是聊天信息
			break;
		}
		default:{
			this.on_msg(msg);
			break;
		}
	}
}

/**
 * 发送指令
 */
websocketUtils.prototype.sendSocketMessage = function(obj){
	if(this.checkWebsocket()){
		webSocket.send(obj);
	}
	if (webSocket.readyState != WebSocket.OPEN) {
		console.log("WebSocket连接没有建立成功!");
		console.log("您还未连接上服务器,请重新登录");
	}
	
}

/**
 * 发送登录事件（只有连接成功但未登录的时候）
 */
websocketUtils.prototype.userLogin = function(option) {
	console.log(websocketConfig);
	if(!this.checkWebsocket()){
		if(!websocketConfig.isConnected){
			this.init(option);
		}
		
	}
	// 只有连接成功但未登录的时候
	if(!this.checkLoginParam(option)){
		return;
	}
	var loginCMD = {cmd:'login'};
	loginCMD["appVersion"] = option.appId;
	loginCMD["roomId"] = option.roomId;
	loginCMD["userName"] = option.userName;
	loginCMD["nickName"] = option.nickName;
	loginCMD["role"] = parseInt(option.role);
	loginCMD["time"] = parseInt(option.time);
	loginCMD["key"] = this.key;
	loginCMD["terminal"] = JSON.parse("{\"clientType\":0,\"clientVersion\":\"" + websocketConfig.version + "\","+getClientInfo()+"}");
	webSocket.send(JSON.stringify(loginCMD));
}

/**
 * 重新登录
 */
websocketUtils.prototype.reLogin = function(param) {
	console.log("重新登录");
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
	} else if(isBlank(option.roomId)){
		err['msg'] = "empty roomId";
	}  else if(isBlank(option.userName)){
		err['msg'] = "empty username";
	} else if(isBlank(option.nickName)){
		option.nickName = option.userName;
	} else if(isBlank(option.role)){
		err['msg'] = "empty role";
	} else if(isBlank(option.time)){
		err['msg'] = "empty time";
	} else if(isBlank(option.key)){
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
	var msgType = cmd.msgType;
	// 登录成功
	if ("onLoginSuccess" === msgType) {
		if (typeof loginSuccessCallBack === "function") {
			loginSuccessCallBack();
		}
	}

	// 登录出现错误时的回调
	if ("onLoginError" === msgType) {
		if (typeof loginErrorCallBack === "function") {
			loginErrorCallBack({
				errorCode : 1,
				msg : "request error"
			}); // 至于错误原因暂时不考虑，后续会加上
		}
	}
	
	// 心跳包响应
	if ("pong" === msgType) {
		
	}
	// 被强制下线之后，不再继续连接服务端
	if ("pong" === msgType) {
		
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
	cmd:"heartBeat",
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
	console.log("closeSocket连接状态" + webSocket.readyState);
	if(null != webSocket && undefined != webSocket){
		websocketConfig.isLogin = false;
		websocketConfig.isConnected = false;
		if (webSocket.OPEN == webSocket.readyState) {
			webSocket.close();
			console.log(webSocket.readyState);
			if (WebSocket.CLOSING== webSocket.readyState) {
				console.log("连接正在关闭....");
			}
		}
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
		
		// 删除历史记录
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
