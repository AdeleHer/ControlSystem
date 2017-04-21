<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>東海迴路控制器與電源管理系統</title>

<link href="./theme/global/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<style>
a:hover {
	text-decoration: none;
}

.bread-nav {
	background-color: #ddd;
}
</style>

</head>
<body>
	<!-- jumbotron -->
	<div class="jumbotron">
		<div class="container">
			<div class="row">
				<h1>
					<a href="goIndex.action">東海迴路控制器與電源管理系統</a>
				</h1>
			</div>
		</div>
	</div>

	<!-- navbar -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="goUserList.action">人員管理</a></li>
					<li><a href="goRoomList.action">空間管理</a></li>
					<li><a href="#">課程檢視</a></li>
					<li><a href="#">控制器管理</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<nav class="navbar navbar-default bread-nav">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="goUserList.action">人員管理</a></li>
					<li class="active"><a href="goPositionList.action">職稱管理</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- content here -->
	<div class="container">
		<div class="row">
			<p>
			<h1>
				職稱管理 (共
				<s:property value="positionNumber" />
				種) <a class="btn btn-primary" href="goPositionAdd.action" role="button">新增職稱</a>
			</h1>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>職稱</th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<s:iterator value="groupList">
						<tr>
							<td><s:property value="key" /></td>
							<td><s:property value="value" /></td>
							<td><a
								href="javascript:editPosition('<s:property value="key"/>')">修改</a> <a
								href="javascript:deletePosition('<s:property value="key"/>')">刪除</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	<form id="PositionForm" style="display: none;" method="post"
		action="goPositionUpdate">
		<input type="text" id="p_sn" name="p_sn">
	</form>

	<script src="./theme/global/jquery/jquery.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/bootstrap.min.js"
		type="text/javascript"></script>
</body>
<script>
	function editPosition(sn) {
		$("#p_sn").val(sn);
		$("#PositionForm").submit();
	}
	function deletePosition(sn) {
		$.ajax({
			type : "post",
			url : "deletePosition.action",
			//	data:"contactform_json={ \"contactform_array\" : "+JSON.stringify(formData) + "}",
			data : "p_sn=" + sn,
			dataType : "json",
			success : function(resp) {
				if (resp.jsonDataMap.state == true) {
					var effect="";
					$.each(resp.effectList,function(key,data){
						effect+=data+"\n";
					})
					alert(resp.jsonDataMap.msg+"\n影響人員清單：\n"+effect);
					location.reload();
				} else {
					alert(resp.jsonDataMap.msg);
				}
			},
			error : function(xhr) {
				alert(xhr.status + ",請向技術人員求助!");
			}
		});
	}
</script>
</html>