<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
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
<s:iterator value="editBuildBean">
	<div class="container">
		<div class="row">
			<p>
			<h1>
				編輯建築物 - 建築   - <s:property value="buildingName" /> (<s:property value="buildingEnName" />)
			</h1>
		</div>
	</div>
	
<form class="form-horizontal" role="form">
<input type="text" style="diaplay:none;" name="" value="<s:property value="buildingSn" />">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">建築物名稱</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" value="<s:property value="buildingName" />" placeholder="輸入電子郵件">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">建築物英文簡稱</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" value="<s:property value="buildingEnName" />" placeholder="輸入電子郵件">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">登入</button>
    </div>
  </div>
</form>
</s:iterator>
</body>
</html>