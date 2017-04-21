<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
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
				<li><a href="goUserList.action">人員管理</a></li>
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
				<li><a href="goPositionList.action">職稱管理</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-1 col-sm-6">
				<h1>職稱管理 - 新增</h1>
			</div>
		</div>
	</div>
	<br>
	<!-- content here -->
	<div class="container">
		<div class="row">
			<form class="form-horizontal" id="addPositionForm">
				<div class="form-group">
					<label class="col-sm-1 control-label">職稱</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="p_name" name="p_name">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-10">
						<a href="javascript:submitForm();" class="btn btn-default">新增</a>
					</div>
				</div>
			</form>
		</div>
	</div>


	<script src="./theme/global/jquery/jquery.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/bootstrap.min.js"
		type="text/javascript"></script>
</body>
<script>
	function submitForm() {
		var name_txt = $("#p_name").val();
		if (name_txt.length > 0) {
			$.ajax({
				type : "post",
				url : "insertPosition.action",
				//	data:"contactform_json={ \"contactform_array\" : "+JSON.stringify(formData) + "}",
				data : $("#addPositionForm").serialize(),
				dataType : "json",
				success : function(resp) {
					if (resp.jsonDataMap.state == true) {
						alert(resp.jsonDataMap.msg);
						window.location.href = "goPositionList.action";
					} else {
						alert(resp.jsonDataMap.msg);
					}
				},
				error : function(xhr) {
					alert(xhr.status + ",請向技術人員求助!");
				}
			});
		}
	}
</script>
</html>