	var table = "#dataBaseTable";
$(function() {
	init();
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			tableName : null
		}
	},
	methods : {
		query : function() {
			init();
		},
		generator : function() {
			var tableNames = getSelectedRows(table);
			if(tableNames == null){
				return;
			}
		}
	}
});


function init(){
	// 先销毁表格
	$(table).bootstrapTable('destroy');

	$(table).bootstrapTableEx({
		url : "/sys/generator/getTableList",
		columns : [ {
			field : "cb",
			checkbox : true,
			hidden : true
		}, {
			field : 'tableName',
			title : '表名'
		}, {
			field : 'engine',
			title : 'Engine'
		}, {
			field : 'tableComment',
			title : '表备注'
		}, {
			field : 'createTime',
			title : '创建日期'
		}, {
			field : 'updateTime',
			title : '修改日期'
		} ],
		onCheck : function(row) {

		},
		onUncheck : function() {

		}
	});
}
//获取查询的参数
function queryParams(params) {
	var temp = { 
		rows : params.pageSize, //页面大小
		page : params.pageNumber,
		tableName:$.trim(vm.q.tableName),
	};
	return temp;
}