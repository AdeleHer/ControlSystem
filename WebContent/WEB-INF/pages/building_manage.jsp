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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<style>
a:hover {
	text-decoration: none;
}

.bread-nav {
	background-color: #ddd;
}

th {
	background-color: #ddd;
}

tbody, td {
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
			<h3>修改/新增建築物</h3>
			<a>下載Excel sample</a>
			<form class="form-horizontal" id="fileForm" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">請選擇欲上傳檔案</label>
					<div class="col-sm-10">
						<input type="file" name="editBuildingFile" id="editBuilding" class="form-control">
					</div>
				</div>
				<input type="text" name="editBuildingFileName" id="editFileName" style="display:none;">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="javascript:submitEditFile();" class="btn btn-default">送出</a>
					</div>
				</div>
			</form>
		</div>
	</div>

	
	<script src="./theme/global/jquery/jquery.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="http://malsup.github.com/jquery.form.js"></script>
</body>
<script>
function changeProgressBar(value){
	$( "#progressbar" ).progressbar({
		  value: value
		});
}
function submitEditFile(){
	var myFile = $('#editBuilding')[0].files[0];
	$("#editFileName").val(myFile.name);
	$.ajax({
		url : "uploadEditBuildingFile.action",
		data : new FormData($('#fileForm')[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : "post",
		success : function(resp) {
			changeProgressBar(100);
             $("#percent").html('100%');
			$.unblockUI();
			if (resp.jsonData.state == true) {
				alert(resp.jsonData.msg);
				window.location.href = "goBuildingList.action";
			} else {
				alert(resp.jsonData.msg);
			}
		},
		error : function(xhr) {
			$.unblockUI();
			toastr.error(xhr.status + ",請向技術人員求助!");
		},
		beforeSend : function() {
        	$.blockUI({
				message : '<h3>上傳中...</h3><div id="progressbox"><div id="progressbar"></div><div id="percent">0%</div></div>'
			});
        },
        uploadProgress : function(event, position, total, percentComplete) {
        		changeProgressBar(percentComplete);
                $("#percent").html(percentComplete + '%');
        }
	});
}
</script>
</html>