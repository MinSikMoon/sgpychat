<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- import bootstrap & jquery & javascript & brython-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/brython.js"></script>
<script type="text/javascript" src="js/brython_stdlib.js"></script>
<script type="text/javascript">
	$(function() {
		//VARIABLES : 변수들
		var OUTPUT_INIT = ''
		var logBackUp = console.log;
		var g_result = OUTPUT_INIT;
		//console overriding : 콘솔 명령어 재정의/ 화면에 보이도록 후킹
		console.log = function(s) {
			if (typeof (s) == 'string') {
				if (!s.startsWith('Error'))
					g_result += s;
			}
			logBackUp(s);
		}
		//#run click function : run 버튼을 누르면 파이썬 스크립트 해석
		$("#run").click(function() {
			var temp = $("#python-source").val();
			$("#python-source").html(temp);
			try {
				brython({
					debug : 1,
					ipy_id : [ 'python-source' ]
				});
			} catch (err) {
			} finally {
				$("#python-output").html(g_result);
				g_result = OUTPUT_INIT;
			}
		});
	})
</script>
<!-- style -->
<style>
html, body, .container {
	height: 100%;
}

.chat-basic {
	overflow: hidden;
	padding-bottom: 100%;
	margin-bottom: -100%;
}

.chat-python {
	/* background-color: #034f84; */
	background-color: #034f84;
}

.chat {
	background-color: #80ced6;
}

.input-group {
	min-width: 100%;
}

.chat-input-group {
	max-width: 85%;
	min-width: 85%;
	margin-top: 20px;
	margin-bottom: 20px;
	float: left;
}

.chat-button-group {
	max-width: 14%;
	min-width: 14%;
	margin-top: 20px;
	margin-bottom: 20px;
	float: right;
	text-align: right;
	height: 90px;

}

.chat-btn {
	min-height: 100%;
	min-width: 100%;
}

.chat-group{
	margin: 0px;
}

.group-title {
	text-align: center;
	color: white;
}

.button-group {
	text-align: right;
	margin-top: 10px;
	margin-bottom: 20px;
}

.python-btn {
	margin-left: 10px;
}
</style>

<title>학생용 채팅 화면입니다.</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6 col-md-6 chat-basic chat-python">
				<h1 class="group-title">파이썬 화면</h1>

				<form action="">
					<div class="input-group">
						<textarea id="python-source" class="form-control" rows="12"
							placeholder="파이썬 스크립트를 붙여넣으세요."></textarea>
					</div>
					<div class="button-group">
						<button id="run" type="button" class="btn btn-primary python-btn">
							<span class="glyphicon glyphicon-play-circle"></span> 실행
						</button>
						<button type="button" class="btn btn-success python-btn">
							<span class="glyphicon glyphicon-send"></span> 소스코드 제출
						</button>
					</div>

					<div class="input-group">
						<textarea readonly id="python-output" class="form-control"
							rows="12" placeholder="결과화면입니다."></textarea>
					</div>

				</form>



			</div>
			<div class="col-lg-6 col-md-6 chat-basic chat">
				<h1 class="group-title">채팅 화면</h1>
				<form>
					<div class="input-group">
						<textarea readonly id="" class="form-control" rows="22"
							placeholder="채팅 화면입니다."></textarea>
					</div>
					<div class="chat-group">
						<div class="chat-input-group">
							<textarea id="" class="form-control" rows="4"
								placeholder="입력하세요."></textarea>
						</div>
						<div class="chat-button-group">
							<button type="button" class="btn btn-success chat-btn">
								<span class="glyphicon glyphicon-send"></span> 전송
							</button>
						</div>
					</div>



				</form>
			</div>
		</div>
	</div>
</body>
</html>