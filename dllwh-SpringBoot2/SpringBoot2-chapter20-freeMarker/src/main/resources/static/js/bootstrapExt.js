/**
 * 基于 bootstrapTable 的扩展
 */
$.fn.bootstrapTableEx = function(opt) {
	var defaults = {
		toolbar:'',
		url : '', 							// 请求后台的URL（*）
		dataField : "rows",
		dataType : 'json',
		selectItemName : 'id',
		smartDisplay : false,
		method: 'post',                     // 请求方式（*）
		toolbar: '',       					// 工具按钮用哪个容器
		iconsPrefix:'glyphicon',            // 定义字体库 ('Glyphicon' or 'fa' for
											// FontAwesome)
		striped: true,                      // 是否显示行间隔色
		cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		sortable: true,                     // 是否启用排序
		queryParamsType: '',
		queryParams: queryParams,			// 传递参数（*）
		sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber: 1,                      // 初始化加载第一页，默认第一页
		pageSize: 10,                       // 每页的记录行数（*）
		pageList: [10, 20, 30, 40, 50],     // 可供选择的每页的行数（*）
		strictSearch: true,
		showHeader:true,					// 是否显示列头
		showFooter:false,					// 是否显示列脚
		showColumns: true,                  // 是否显示所有的列
		showRefresh: true,                  // 是否显示刷新按钮
		showToggle: false,                  // 是否显示详细视图和列表视图的切换按钮
		minimumCountColumns: 2,             // 最少允许的列数
		clickToSelect: true,                // 是否启用点击选中行
		cardView: false,                    // 是否显示详细视图
		detailView: false,                  // 是否显示父子表
		pagination: true,                   // 是否显示分页（*）
		paginationLoop:false,				// 设置为 true 启用分页条无限循环的功能
		onlyInfoPagination:false,			// 设置为 true 只显示总数据数，而不显示分页按钮
		paginationPreText:"上一页",			// 指定分页条中上一页按钮的图标或文字
		paginationNextText:"下一页",			// 指定分页条中下一页按钮的图标或文字
		clickToSelect:true,					// 设置true 将在点击行时，自动选择rediobox 和
											// checkbox
		singleSelect:true,					// 设置True 将禁止多选
		maintainSelected:false,				// 设置为 true
											// 在点击分页按钮或搜索按钮时，将记住checkbox的选择项
		contentType: "application/x-www-form-urlencoded",
		columns : [],
		formatDetailPagination : function(totalRows) {
			return "总共显示 " + totalRows + " 条记录";
		},
		onLoadSuccess: function(data){  // 加载成功时执行
			if(dllwh.isNotNullOrEmpty(data.success)&& !data.success){
				alert(data.msg);
			}
		},
		onLoadError: function(status){  // 加载失败时执行
			alert("数据加载失败，请重新刷新页面");
		},
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			if(totalRows >0){
				return "显示第 " + pageFrom + " 到第 " + pageTo + " 条记录，总共 " + totalRows + " 条记录"
			} else {
				return "";
			}
		},
		formatNoMatches: function () {// 没有匹配的结果
			$(".page-next").addClass('disabled');
			return '无符合条件的记录';
		}
	}
	var option = $.extend({}, defaults, opt);
	$(this).bootstrapTable(option);
}

/**
 * 获取查询的参数
 */
function queryParams(params) {
	// 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	var temp = {   
		pageSize : params.pageSize, // 页面大小
		pageNumber : params.pageNumber,// 页码
		sortName: params.sortName,// 排序列名
		sortOrder: params.sortOrder// 排位命令（desc，asc）
	};
	return temp;
}

/**
 * 基于Bootstrap-fileinput 扩展
 */
$.fn.bootstrapFileInput = function(opt) {
	var defaultConfig = {
		"language": "zh", // 设置语言
		"uploadAsync" : true, // false 同步上传，后台用数组接收，true
								// 异步上传，每次上传一个file,会调用多次接口
		"showCaption" : true, // 是否显示标题
		"showUpload" : true, // 是否显示上传按钮
		"showRemove" : true, // 是否显示移除按钮
		"showPreview" : true, // 是否显示预览按钮
		"showCancel" : true,// 是否显示取消按钮
		"showClose" : true,// 是否显示关闭按钮
		"showBrowse" : true,// 是否显示浏览按钮
		"dropZoneEnabled" : false, // 是否显示拖拽区域
		"browseOnZoneClick": true,
		"validateInitialCount":true,
		"overwriteInitial": true,
		"maxFileSize" : 1024*1024, // 允许上传最大附件,单位为kb，如果为0表示不限制文件大小
		"maxFileCount" : 11,// 最大允许上传附件的个数，默认是1
		"minFileCount":1,// 最小允许上传附件的个数，默认是1
		"allowedFileExtensions" : [ 
		                            "xls", "xlsx", "ppt", "pptx", "doc", "docx","pdf",
		                            "zip","rar","tar","gzip","gz","7z",
		                            "BMP","JPG","JPEG","PNG","GIF",
		                            "avi","mpg","mkv","mov","mp4","3gp","webm","wmv","mp3","wav"], // 接收的文件后缀
		"removeFromPreviewOnError":true,// 当选择的文件不符合规则时,选择的文件不会出现在预览框中,只会显示错误信息
		"layoutTemplates" :{
			// "actionDelete":'', //去除上传预览的缩略图中的删除图标
			// "actionUpload":'',//去除上传预览缩略图中的上传图片；
			// "actionZoom":'' //去除上传预览缩略图中的查看详情预览的缩略图标。
		},
		"previewFileIconSettings": { // configure your icon file extensions
			"doc"     :'<i class="fa fa-file-word-o text-primary"></i>',
			"docx"    :'<i class="fa fa-file-word-o text-primary"></i>',
			"xls"     :'<i class="fa fa-file-excel-o text-success"></i>',
			"xlsx"    :'<i class="fa fa-file-excel-o text-success"></i>',
			"ppt"     :'<i class="fa fa-file-powerpoint-o text-danger"></i>',
			"pptx"    :'<i class="fa fa-file-powerpoint-o text-danger"></i>',
			"jpg"     :'<i class="fa fa-file-photo-o text-warning"></i>',
			"pdf"     :'<i class="fa fa-file-pdf-o text-danger"></i>',
			"zip"     :'<i class="fa fa-file-archive-o text-muted"></i>',
			"rar"     :'<i class="fa fa-file-archive-o text-muted"></i>',
			'pdf'     :'<i class="fa fa-file-pdf-o text-danger"></i>',  
			'gif'     :'<i class="fa fa-file-photo-o text-muted"></i>',  
			'png'     :'<i class="fa fa-file-photo-o text-primary"></i>', 
			'htm'     :'<i class="fa fa-file-code-o text-info"></i>',
			'txt'     :'<i class="fa fa-file-text-o text-info"></i>',
			'mov'     :'<i class="fa fa-file-movie-o text-warning"></i>',
			'mp3'     :'<i class="fa fa-file-audio-o text-warning"></i>',
			"default" :"<i class='fa fa-file-o'></i>"
		},
		"uploadSuccessHandler" : null,
		"uploadErrorHandler" :null,
		"batchuploaderrorHandler":null,
		"batchuploadSuccessHandler":null 
	};
	
	
	var option = $.extend({}, defaultConfig, opt);
	$(this).fileinput(option);
	
	// 同步上传错误结果处理
	$(this).on('filebatchuploaderror', function(event, data, msg) {
		if(dllwh.isNotNullOrEmpty(option.batchuploaderrorHandler) 
			&& dllwh.isExitsFunction(option.batchuploaderrorHandler)){
			option.uploadErrorHandler(data, msg);
		} else {
			alert(msg);
		}
	});
	
	// 同步上传成功结果处理
	$(this).on("filebatchuploadsuccess",function(event, data){
		if(dllwh.isNotNullOrEmpty(option.batchuploadSuccessHandler)
				&& dllwh.isExitsFunction(option.batchuploadSuccessHandler)){
			option.batchuploadSuccessHandler(data);
		} else {
			alert(msg);
		}
	});
	
	// 异步上传错误结果处理
	$(this).on("fileerror", function(event, data, msg) {
		if(dllwh.isNotNullOrEmpty(option.uploadErrorHandler)
			&& dllwh.isExitsFunction(option.uploadErrorHandler)){
			option.uploadErrorHandler(data, msg);
		} else {
			alert(msg+"……");
		}
	});
	
	// 上传结果的回调函数
	$(this).on("fileuploaded", function(event, data, previewId, index) {
		// data: 这是一个数据对象(关联数组)发送以下信息
		// files: 上传的文件信息
		// response: ajax后台服务响应的内容
		// jqXHR: jQuery XMLHttpRequest对象，响应报文
		// previewId::每个文件的标识符的母公司缩略图预览窗口中的div元素
		// index: 从零开始的索引文件中文件的堆栈
		if (data == undefined) {
			alert("文件格式类型不正确", "warn");
			return;
		}
		var response = data.response; 
		if (dllwh.isNotNullOrEmpty(option.uploadSuccessHandler) 
				&& dllwh.isExitsFunction(option.uploadSuccessHandler)){
			option.uploadSuccessHandler(response);
		} else {
			alert("文件已成功上传!",);
		}
	});
};

// 选择一条记录
function getSelectedRow(tableId) {
	var selectedRow = $(tableId).bootstrapTable("getSelections");
	if (dllwh.isNullOrEmpty(selectedRow)) {
		alert("请选择一条记录");
		return;
	}

	if (selectedRow.length > 1) {
		alert("只能选择一条记录");
		return;
	}

	return selectedRow[0];
}

// 选择多条记录
function getSelectedRows(tableId) {
	var selectedRows = $(tableId).bootstrapTable('getSelections');
	if (dllwh.isNullOrEmpty(selectedRows)) {
		alert("请选择一条记录");
		return;
	}

	return selectedRows;
}