/**
 * @param title
 *            标题
 * @param url
 *            请求的url
 * @param id
 *            需要操作的数据id
 * @param width
 *            弹出层宽度（缺省调默认值）
 * @param height
 *            弹出层高度（缺省调默认值）
 * @param full
 *            是否全屏（缺省调默认值）
 * 
 */
var dialogOpen = function(title, url, width, height, full=false) {
	if (title == null || title == '') {
		title = false;
	}
	if (url == null || url == '') {
		url = "syspage/404";
	}
	if (width == null || width == '') {
		width = ($(window).width() * 0.9);
	}
	if (height == null || height == '') {
		height = ($(window).height() - 50);
	}
	
	var index = layer.open({
		type : 2,
		area : [ width + 'px', height + 'px' ],
		fix : false, // 不固定
		maxmin : true,
		shadeClose : true,
		shade : 0.4,
		title : title,
		content : url,
		success : function() {
			// 窗口加载成功刷新frame
			// location.replace(location.href);
		},
		cancel : function() {
			// 关闭窗口之后刷新frame
			// location.replace(location.href);
		},
		end : function() {
			// 窗口销毁之后刷新frame
			// location.replace(location.href);
		}
	});
	if (full) {
		layer.full(index);
	}
}


var getIcon = function(iconType){
	var msgType = {
		success : 1,
		error : 2,
		warn : 3,
		info : 7
	};
	
	var icon = msgType[iconType];
	if (icon == null || icon == '') {
		icon = 7;
	}
	return icon;
}

/**
 * 普通信息框
 * 
 * @param content
 *            内容
 * @param type
 *            内容 iconType
 */
var dialogAlert = function(content, iconType) {
	layer.alert(content, {
		icon : getIcon(iconType),
		title : "系统提示",
		anim : -1,
		btnAlign : 'c',
		move : false,
		isOutAnim : false
	});
}
/**
 * 询问框
 * 
 * @param content
 * @param callBack
 *            “确认”回调事件
 */
var dialogConfirm = function(content, callBack) {
	layer.confirm(content, {
		area : '338px',
		icon : 7,
		anim : -1,
		isOutAnim : false,
		title : "系统提示",
		btn : [ '确认', '取消' ],
		btnAlign : 'c',
		move : false,
		yes : callBack
	});
}
/**
 * 提示框
 */
var dialogMsg = function(msg, iconType) {
	layer.msg(msg, {
		move : false,
		icon : getIcon(iconType),
		time : 3000
	});
}

/**
 * 关闭弹出框口
 */
var dialogClose = function() {
	// 先得到当前iframe层的索引
	var index = top.layer.getFrameIndex(window.name);
	// var index = parent.layer.getFrameIndex(window.name);
	// 再执行关闭
	top.layer.close(index); 
	// parent.layer.close(index);
}

/**
 * 加载层
 */
var dialogLoading = function(flag) {
	if (flag) {
		layer.load(0, {
			shade : [ 0.1, '#fff' ],
			time : 2000
		});
	} else {
		dialogCloseAll('loading');
	}
}