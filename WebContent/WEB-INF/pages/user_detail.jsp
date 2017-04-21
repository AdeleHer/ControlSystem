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

	<!-- content here -->

	<!-- header -->
	<s:iterator value="userInfo">
		<div class="container">
			<div class="row">
				<h1>
					人員管理 - 詳細資訊 -
					<s:property value="name" />
				</h1>
			</div>
		</div>

		<!-- room info -->
		<div class="container">
			<div class="row">

				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>帳號</th>
							<th>姓名</th>
							<th>職稱</th>
							<th>電話</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><s:property value="sn" /></td>
							<td><s:property value="account" /></td>
							<td><s:property value="name" /></td>
							<td><s:property value="groupString" /></td>
							<td><s:property value="phone" /></td>
						</tr>
					</tbody>
				</table>

			</div>
		</div>
	</s:iterator>

	<script src="./theme/global/jquery/jquery.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/bootstrap.min.js"
		type="text/javascript"></script>
</body>
<script>
	
</script>
</html>