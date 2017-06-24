<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- import bootstrap & jquery & javascript -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Styles -->
<style type="text/css">
html, body, .container {
	background-color: #034f84;
	height: 100%;
}
.container {
    display: table;
    vertical-align: middle;
}
.vertical-center-row {
    display: table-cell;
    vertical-align: middle;
}

#welcome {
	color:white;
	background-color: #034f84;
	margin:0;
}
#make_room{
	border-radius: 6px;
}
</style>


<title>수업 방만들기 화면</title>
</head>
<body>
	<div class="container" >
		<div id = "welcome" class = "text-center jumbotron">
			<h1>서강 파이썬 수업 채팅</h1>
		</div>
		<div id = "make_room" class = "text-center">
			<a href="make"><button type="button" class = "btn btn-warning btn-lg">수업 방만들기</button></a>
		</div>
	</div>
</body>
</html>