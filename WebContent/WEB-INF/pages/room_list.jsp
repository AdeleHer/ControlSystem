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
th{
	background-color:#ddd;
}
tbody,tr,td{
	border: 1px solid #ddd;
}
</style>

</head>
<body>
<div class="jumbotron">
		<div class="container">
			<div class="row">
				<h1>
					<a href="goIndex.action">東海迴路控制器與電源管理系統</a>
				</h1>
			</div>
		</div>
	</div>
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
					<li class="active"><a href="#">空間管理</a></li>
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
					<li class="active"><a href="goRoomList.action">空間管理</a></li>
					<li><a href="goBuildingList.action">建築物管理</a></li>
				</ul>
			</div>
		</div>
	</nav>
<div class="container">
	<s:iterator value="roomList" var="room">
		<div class="row">
			<table class="table table-striped">
				
				<tbody>
					<tr>
						<th>
							<a href="javascript:goCourseInfo('<s:property value="#room.roomSn" />')"><s:property value="#room.roomName" /></a>
							現有：<s:property value="#room.scheduleCount.size()"/>個課程使用
							<a href="javascript:showCourseDetail('<s:property value="#room.roomSn" />');">more</a>
							<s:if test="%{#room.remark!=null}">
							用途： <s:property value="#room.roomRemark" />
							 </s:if>
							 
						</th>
					</tr>
					<tr id="<s:property value="#room.roomSn" />" style="display:none;">
						<td>
							<s:iterator value="#room.courseList" var="cList">
							<p style="font-size:8pt;"><s:property value="#cList.courseid" /> - <s:property value="#cList.name" /></p>
							</s:iterator>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</s:iterator>
	</div>
	<form style="display:none;">
		<input type="text" id="roomSn" name="roomSn">
	</form>
	<script src="./theme/global/jquery/jquery.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/bootstrap.min.js"
		type="text/javascript"></script>
</body>
<script>
function showCourseDetail(id){
	$("#"+id).toggle();
}
function goCourseInfo(sn){
	$("#roomSn").val(sn);
	
}
</script>
</html>