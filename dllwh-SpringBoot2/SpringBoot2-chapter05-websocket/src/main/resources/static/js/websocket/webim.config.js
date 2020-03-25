/**
 * 
 */
var webIM = {};
webIM.config = {
	protocol : 'ws://',
	server : 'localhost',
	port : '8205',
	policyPort : 843,
	isBrowser : typeof window === 'object' && window.window === window,
	version : '1.0.0',
	/**
	 * 连接标识,第一次连接成功置为true,主动断开置为false,为true时，因网络原因断开时自动重连
	 */
	isConnected : false,
	/**
	 * 
	 */
	isFocus: true,
	/**
	 * 是否自动登录
	 */
	isAutoLogin : false,
	/**
	 * 登录状态
	 */
	isLogin : false,
	/**
	 * 是否发送心跳包
	 */
	isSendHeartBeat : false,
	/**
	 * 心跳发送时间间隔
	 */
	heartBeatInterval : 3000,
	/**
	 * 是否开启断网自动重连，避免不间断的重连操作
	 */
	isReconnect : false,
	/**
	 * 被动断开连接时，重连时间间隔
	 */
	autoReconnectInterval : 3000,
	/**
	 * 最大重连次数（-1为无限制）
	 */
	autoReconnectNumMax : -1,
	/**
	 * 重连成功后，是否重新发送之前发送失败的消息
	 */
	isReconnectedResend : false,
	/**
	 * 断开连接后，保留失败消息的条数
	 */
	unReconnectMsgCount : 10,
	/**
	 * 间隔时间内未接收到服务端心跳，断开连接
	 */
	unliveTime : 10000,
	/**
	 * 是否输出控制台日志
	 */
	isDebug : false,
	/**
	 * 自动回复内置文案，也可动态读取数据库配置
	 */
	autoReplay : [ 
		'您好，我现在有事不在，一会再和您联系。',
		'你没发错吧？', 
		'洗澡中，请勿打扰，偷窥请购票，个体四十，团体八折，订票电话：一般人我不告诉他！',
		'你好，我是主人的美女秘书，有什么事就跟我说吧，等他回来我会转告他的。', 
		'我正在拉磨，没法招呼您，因为我们家毛驴去动物保护协会把我告了，说我剥夺它休产假的权利。', '<（@￣︶￣@）>',
		'你要和我说话？你真的要和我说话？你确定自己想说吗？你一定非说不可吗？那你说吧，这是自动回复。', 
		'主人正在开机自检，键盘鼠标看好机会出去凉快去了，我是他的电冰箱，我打字比较慢，你慢慢说，别急……',
		'(*^__^*) 嘻嘻，是贤心吗？' ]
}


if (webIM.config.isBrowser) {
	if (location.protocol == 'https:') {
		webIM.config.protocol = 'wss://';
	}
}

/**
 * 当前用户信息
 */
webIM.currentUser = {
	/**
	 * 用户角色。 -1:游客;0:普通;1:群主;2:管理员;
	 */
	userRole : -1
}

webIM.api = {
	/**
	 * 好友列表接口
	 */
	getFriendList : '/api/friend/getFriendList.json',
	/**
	 * 群组列表接口
	 */
	getGroupList : '/api/group/getGroupList.json',
	/**
	 * 聊天记录接口
	 */
	getChatlog : '/api/group/getChatlog.json',
	/**
	 * 群组成员接口
	 */
	getGroupMember : '/api/group/getGroupMember.json'
}