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
<link href="./theme/global/css/bootstrap-datepicker3.min.css"
	rel="stylesheet" type="text/css" />

<style>
a:hover {
	text-decoration: none;
}
.bread-nav{
			background-color:#ddd;
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
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="goUserList.action">人員管理</a></li>
					<li><a href="goPositionList.action">職稱管理</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<s:iterator value="userInfo">
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-1 col-sm-6">
				<h1>人員管理 - 修改 - <s:property value="name" /></h1>
			</div>
		</div>
	</div>
	<br>
	<!-- content here -->
	<div class="container">
		<div class="row">
			<form class="form-horizontal" id="updateUserForm">
				<input type="text" id="u_sn" name="u_sn" value="<s:property value="sn" />" style="display:none;">
				<div class="form-group">
					<label class="col-sm-1 control-label">帳號</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="<s:property value="account" />" name="u_account">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">密碼</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" value="<s:property value="password" />" name="u_password">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">姓名</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="<s:property value="name" />" name="u_name">
					</div>
				</div>
				<div class="form-group mt-repeater">
					<div data-repeater-list="group-c">
						<s:iterator value="groupSet" var="groupSet">
						<div data-repeater-item class="mt-repeater-item"
							style="margin-top: 10px;">
							<div class="row mt-repeater-row">
								<label class="col-sm-1  control-label"
									style="margin-left: 13px;">職稱</label>
								<div class="col-sm-5">
									<s:select class="form-control" list="groupList" value="%{#groupSet}" name="GroupList" theme="simple">
									</s:select>
								</div>
								<div class=" col-sm-1">
									<a href="javascript:;" data-repeater-delete
										class="btn btn-danger mt-repeater-delete"> X </a>
								</div>
							</div>
						</div>
						</s:iterator>
					</div>
					<a href="javascript:;" style="margin-top: 10px;"
						data-repeater-create
						class="btn btn-info mt-repeater-add col-md-offset-1"> <i
						class="fa fa-plus"></i>新增欄位
					</a>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">電話</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" value="<s:property value="phone" />" name="u_phone">
					</div>
				</div>
				<input type="text" id="u_group" style="display:none;" class="form-control" name="u_group">
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-10">
						<a href="javascript:readySubmit();" class="btn btn-default">送出</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</s:iterator>

	<script src="./theme/global/jquery/jquery.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/jquery.repeater.min.js"
		type="text/javascript"></script>
	<script src="./theme/global/jquery/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
</body>
<script>
	var groupSet;
	function checkGroup() {
		var isRepeat = false;
		groupSet = new Set();
		var groupInput = $("select[name*='GroupList']");
		$.each(groupInput, function() {
			if ($(this).val() == null) {
				alert("不能有欄位為空");
				isRepeat = true;
			} else if (groupSet.has($(this).val())) {
				alert("重複職位:" + $(this).find(":selected").text());
				isRepeat = true;
			} else {
				groupSet.add($(this).val());
			}
		});
		return isRepeat;
	}
	function readySubmit() {
		if (!checkGroup()) {
			$("#u_group").val([...groupSet]);
			$.ajax({
				type : "post",
				url : "updateUser.action",
				//	data:"contactform_json={ \"contactform_array\" : "+JSON.stringify(formData) + "}",
				data : $("#updateUserForm").serialize(),
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
			/*groupSet.forEach(function(value) {
				console.log(value);
			});*/
		}
	}
	var FormRepeater = function() {
		return {
			//main function to initiate the module
			init : function() {
				$('.mt-repeater').each(function() {
					$(this).repeater({
						show : function() {
							$(this).slideDown();
							$('.date-picker').datepicker({
								orientation : "left",
								autoclose : true
							});

						},

						hide : function(deleteElement) {
							$(this).slideUp(deleteElement);
						},

						ready : function(setIndexes) {

						}

					});
				});
			}

		};

	}();
	$(document).ready(function() {
		FormRepeater.init();
	});
</script>
</html>