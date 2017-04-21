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

tbody, td{
	border: 1px solid #ddd;
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
					<li class="active"><a href="goRoomList.action">空間管理</a></li>
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
					<li><a href="goRoomList.action">空間管理</a></li>
					<li class="active"><a href="goBuildingList.action">建築物管理</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- content here -->
	<div class="container">
		<div class="row">
			<p>
			<h1>
				建築物管理 (共
				<s:property value="buildingList.size()" />
				棟) <a class="btn btn-primary" href="goBuildingManage.action"
					role="button">新增建築物</a>
			</h1>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<table class="table building-table">
				<thead>
					<tr>
						<th>#</th>
						<th>建築物名稱</th>
						<th>英文縮寫</th>
						<th>樓層</th>
						<th>教室</th>
						<th>教室用途</th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<s:iterator value="buildingList" var="build">
							<tr>
							<td rowspan="<s:property value="#build.roomNumber" />"><s:property
									value="#build.buildingSn" /></td>
							<td rowspan="<s:property value="#build.roomNumber" />"><s:property
									value="#build.buildingName" />
									<a href="javascript:editBuilding('<s:property
									value="#build.buildingSn" />');" class="btn btn-primary">編輯</a><a href="javascript:deleteBuilding('<s:property
									value="#build.buildingSn" />')" class="btn btn-danger">刪除</a>
									</td>
							<td rowspan="<s:property value="#build.roomNumber" />"><s:property
									value="#build.buildingEnName" /></td>
							<s:iterator value="#build.floorBeanList" var="floor">
								<tr>
									<td rowspan="<s:property value="#floor.roomNumber" />"><s:property
											value="#floor.floorName" />
											<a href="javascript:editFloor('<s:property
									value="#floor.floorSn" />');" class="btn btn-primary">編輯</a><a href="javascript:deleteFloor('<s:property
									value="#floor.floorSn" />')" class="btn btn-danger">刪除</a>
											</td>
								</tr>
								<s:iterator value="#floor.roomBeanList" var="room">
									<tr>
										<td><s:property value="#room.roomName" />
										<a href="javascript:editRoom('<s:property value="#room.roomSn" />');" class="btn btn-primary">編輯</a>
										<a href="javascrupt:deleteRoom('<s:property value="#room.roomSn" />');" class="btn btn-danger">刪除</a>
										</td>
										<td><s:property value="#room.roomRemark" /></td>
									</tr>
								</s:iterator>
							</s:iterator>
							</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	<form id="editBuildingForm" style="display: none;" method="post"
		action="goBuildingUpload.action">
		<input type="text" id="edit_building_sn" name="editSn">
	</form>
	<form id="editFloorForm" style="display: none;" method="post"
		action="goFloorUpload.action">
		<input type="text" id="edit_floor_sn" name="editSn">
	</form>
	<form id="editRoomForm" style="display: none;" method="post"
		action="goRoomUpload.action">
		<input type="text" id="edit_room_sn" name="editSn">
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
					var effect = "";
					$.each(resp.effectList, function(key, data) {
						effect += data + "\n";
					})
					alert(resp.jsonDataMap.msg + "\n影響人員清單：\n" + effect);
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