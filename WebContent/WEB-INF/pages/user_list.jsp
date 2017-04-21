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
					<li class="active"><a href="goUserList.action">人員管理</a></li>
					<li><a href="goPositionList.action">職稱管理</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- content here -->
	<div class="container">
		<div class="row">
			<p>
			<h1>
				人員管理 (共
				<s:property value="userNumber" />
				人) <a class="btn btn-primary" href="goUserAdd.action" role="button">新增人員</a>
			</h1>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>姓名</th>
						<th>職稱</th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<s:iterator value="userList">
						<tr>
							<td><s:property value="sn" /></td>
							<td><a
								href="javascript:getMoreInfo('<s:property value="sn"/>','<s:property value="groupString" />')"><s:property
										value="name" /></a></td>
							<td><s:property value="groupString" /></td>
							<td><a
								href="javascript:editUser('<s:property value="sn"/>')">修改</a> <a
								href="javascript:deleteUser('<s:property value="sn"/>')">刪除</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	<form id="uniUserForm" style="display: none;" method="post"
		action="getUniUserInfo">
		<input type="text" id="getInfoUserSn" name="getInfoUserSn">
	</form>
	<form id="UserForm" style="display: none;" method="post"
		action="goUserEdit">
		<input type="text" id="editUserSn" name="editUserSn">
	</form>

	<script src="./theme/global/jquery/jquery.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/bootstrap.min.js"
		type="text/javascript"></script>
</body>
<script>
	function getMoreInfo(sn, group) {
		var str = sn + "/" + group;
		$("#getInfoUserSn").val(str);
		$("#uniUserForm").submit();
	}
	function editUser(sn) {
		$("#editUserSn").val(sn);
		$("#UserForm").submit();
	}
	function deleteUser(sn) {
		$.ajax({
			type : "post",
			url : "deleteUser.action",
			//	data:"contactform_json={ \"contactform_array\" : "+JSON.stringify(formData) + "}",
			data : "deleteUserSn=" + sn,
			dataType : "json",
			success : function(resp) {
				if (resp.jsonDataMap.state == true) {
					alert(resp.jsonDataMap.msg);
					window.location.href = "goUserList.action";
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