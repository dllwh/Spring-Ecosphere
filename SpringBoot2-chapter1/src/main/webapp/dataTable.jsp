<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jquery DataTable基本使用</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<%--分页引入 --%>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />

<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
</head>
<body>

	<table border="1px" width="100%" id="myTable" class="tablelist">
		<thead>
			<tr>
				<th>序号</th>
				<th>账号</th>
				<th>真实姓名</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList }" var="user" varStatus="varStatus">
			<tr>
				<td>${varStatus.count }</td>
				<td>${user.userName }</td>
				<td>${user.realName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		
		
		$(document).ready(function() {
			$('#myTable').DataTable({
				"searching" : false,// 是否允许检索
				"ordering" : true,// 是否允许排序
				//"order": [[0,'asc'],[1,'desc']], // 初期排序列
				"sort": true, //是否启动各个字段的排序功能
				"info" : true, // 是否显示页脚信息  就是"当前显示1/100记录"这个信息
				"paging" : true, // 是否允许翻页，设成false，翻页按钮不显示
				"scrollX" : false, // 水平滚动条
				"scrollY" : false, // 垂直滚动条
				"lengthChange" : false, // 件数选择功能 默认true
				"lengthMenu" : [ 10, 25, 50, 75, 100 ], // 件数选择下拉框内容
				"pageLength" : 10, // 每页的初期件数 用户可以操作lengthMenu上的值覆盖
				"autoWidth" : true, // 自动列宽
				"processing" : true, // 是否表示 "processing" 加载中的信息，这个信息可以修改
				"destroy" : false, // 每次创建是否销毁以前的DataTable,默认false
				"language" : {
					"processing" : "正在获取数据，请稍后……",
					"lengthMenu" : "每页显示 _MENU_ 条记录",// 当前页显示多少条
					// _START_（当前页的第一条的序号） ,_END_（当前页的最后一条的序号）,_TOTAL_（筛选后的总件数）,
					// _MAX_(总件数),_PAGE_(当前页号),_PAGES_（总页数）
					"info" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
					// 没有数据的显示（可选），如果没指定，会用zeroRecords的内容
					"emptyTable" : "表中无数据存在！",
					// 筛选后，没有数据的表示信息，注意emptyTable优先级更高
					"zeroRecords" : "抱歉， 没有找到相关数据",
					"infoEmpty": "没有数据",
					"searchPlaceholder": "🔍 查找",//搜索框内占位符
					"search": "",//搜索框前的字体
					// 翻页按钮文字控制
					"paginate" : {
						"first" : "首页",
						"last" : "后一页",
						"next" : "尾页",
						"previous" : "前一页"
					},
					// Client-Side用，Server-Side不用这个属性
					"loadingRecords" : "Please wait - loading..."
				},
			});
		});
	</script>
</body>
</html>