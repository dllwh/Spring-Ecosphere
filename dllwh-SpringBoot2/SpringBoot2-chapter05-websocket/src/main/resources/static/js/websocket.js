var websocketUtils = function() {
}
// 连接状态
var isConnected = false;
// 登录状态
var isLogin = false;

// 初始化连接参数
websocketUtils.prototype.init = function() {

}

// 连接方法
websocketUtils.prototype.connect = function() {

}

// 连接方法
websocketUtils.prototype.initSocket = function() {
	// WebSocket客户端 PS：URL开头表示WebSocket协议 中间是域名端口 结尾是服务端映射地址
	var webSocket = new WebSocket('ws://localhost:8080/chat');
}

// 当打开连接时触发
websocketUtils.prototype.onopen = function(event) {
	console.log('WebSocket打开连接');
}

// 当连接关闭时触发
websocketUtils.prototype.onclose = function(event) {
	console.log('WebSocket关闭连接');
}

// 当通信异常时触发
websocketUtils.prototype.onerror = function(event) {
	console.log('WebSocket发生异常');
}

// 当客户端接收服务端数据时触发
websocketUtils.prototype.onmessage = function(event) {
	console.log('WebSocket收到消息：%c' + event.data, 'color:green');
	// 登录响应指令
	// 当前用户连接断开通知
	// 群组消息
	// 收到消息后，将消息发送给页面
	if(onMessageReceived instanceof function){
		onMessageReceived(message);
	}
}

// 重新登录
websocketUtils.prototype.reLogin = function() {
}

// 重新连接
websocketUtils.prototype.reConnect = function() {

}

// 登录方法
websocketUtils.prototype.login = function() {
	var loginCmd = {
		cmd : 'login'
	};
	loginCmd["appid"] = this.appid;
	loginCmd["roomid"] = this.roomid;
	loginCmd["userName"] = this.username;
	loginCmd["role"] = parseInt(this.role);
	loginCmd["time"] = parseInt(this.time);
	loginCmd["key"] = this.key;
}

// 发送指令
websocketUtils.prototype.send = function(obj) {

}

// 发送群组消息
websocketUtils.prototype.publicGroupMsg = function(cmd, content) {

}

// 发送聊天消息
websocketUtils.prototype.publicChatMessage = function(msg, type) {

}

// 验证登录参数
websocketUtils.prototype.checkLoginParam = function() {
	return true;
}

// 通知列表
websocketUtils.prototype.noticeList = function(data) {
}

// 当前在线列表
websocketUtils.prototype.onlineUserList = function(data) {

}

// 在线人数
websocketUtils.prototype.onUserCountMessage = function(data) {
	console.log("当前在线人数[onUserCountMessage]：", data);
}

// 系统消息(广播消息回调)
websocketUtils.prototype.onBroadcastMsg = function(data) {
	console.log('系统消息：' + data.content);
}

// 接收私聊
websocketUtils.prototype.onPrivateChatMessage = function(data) {
	
}

// 接收公聊
websocketUtils.prototype.onPublicChatMessage = function(data) {
}

// 接收私聊回复
websocketUtils.prototype.onPrivateAnswer = function(data) {
}

// 页面布局配置
websocketUtils.prototype.onRoomSetting = function(data) {
}

// 踢出
websocketUtils.prototype.onKickOut = function(data) {
	console.log(data);
}

// 已经在聊天的列表信息
websocketUtils.prototype.onLiveInteractionChatusers = function() {

}
