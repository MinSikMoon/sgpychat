<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		//탭닫기 & 브라우저 닫기 시 로그아웃 테스트
		/* $(window).on("beforeunload", function() {
			var roomKey = $("#roomKey").val();
			$.get("exit/" + roomKey, {}, function(data) {
				alert("close 보내기 성공");
			});
		}) */
		var roomKey = $("#roomKey").val();
		var webSocket = new WebSocket("ws://localhost:8080/sgpychat/testing/ws");
		//웹 소켓이 연결되었을 때 호출되는 이벤트
		webSocket.onopen = function(message) {
			$("#received-content").val("교수님과 연결되었습니다.");
		};
		//웹 소켓이 닫혔을 때 호출되는 이벤트
		webSocket.onclose = function(message) {
			var tempContent = $("#received-content").val() + "\n ==>교수님과 연결이 끊어졌습니다=="
			$("#received-content").val(tempContent);
		};
		//웹 소켓이 에러가 났을 때 호출되는 이벤트
		webSocket.onerror = function(message) {
			$("#received-content").val("error...\n")
		};
		//웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
		webSocket.onmessage = function(message) {
			var obj = JSON.parse(message.data);
			var time = obj.date;
			var content = obj.content;
			var tempContent = $("#received-content").val() + "\n*교수님으로부터 => "
			+ content + "\n" + time + "==========\n";
			$("#received-content").val(tempContent);
		};
		//Send 버튼을 누르면 실행되는 함수 : 메시지를 json으로 만든다. 
		$("#chat-send").click(function() {
			//시간 객체
			var date = new Date();
			var time = date.toLocaleDateString() + ", " + date.toLocaleTimeString();
			var sendContent = $("#send-content").val();
			var msg = {
				type : "client",
				name: $("#name").val(),
				content: sendContent,
				content_type: "chat",
				roomKey : $("#roomKey").val(),
				date : time
			};
			//웹소켓으로 textMessage객체의 값을 보낸다.
			webSocket.send(JSON.stringify(msg));
			//내 화면에도 추가
			var tempContent = $("#received-content").val() + "\n*나 => "
			+ sendContent + "\n" + time + "==========\n";
			$("#received-content").val(tempContent);
			//textMessage객체의 값 초기화
			$("#send-content").val('');
		})
		//소스코드 제출 누르면 실행되는 함수.
		$("#src-send").click(function() {
			//시간 객체
			var date = new Date();
			var time = date.toLocaleDateString() + ", " + date.toLocaleTimeString();
			var msg = {
				type : "client",
				name: $("#name").val(),
				content : $("#python-source").val(),
				content_type: "source",
				roomKey : $("#roomKey").val(),
				date : time
			};
			//웹소켓으로 textMessage객체의 값을 보낸다.
			webSocket.send(JSON.stringify(msg));
			//textMessage객체의 값 초기화
			$("#send-content").val('');
		})
		//웹소켓 종료
		function disconnect() {
			webSocket.close();
		}

	})
</script>

<!-- 웹소켓 전용  -->

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
	margin-top: 10px;
	margin-bottom: 20px;
	float: left;
}

.chat-button-group {
	max-width: 14%;
	min-width: 14%;
	margin-top: 10px;
	margin-bottom: 20px;
	float: right;
	text-align: right;
	height: 90px;
}

.chat-btn {
	min-height: 100%;
	min-width: 100%;
}

.chat-group {
	margin: 0px;
}

.group-title {
	text-align: center;
	color: white;
	margin-top: 10px;
}

.button-group {
	text-align: right;
	margin-top: 10px;
	margin-bottom: 10px;
}

.python-btn {
	margin-left: 10px;
}

.top-bar {
	text-align: right;
	margin: 0px;
	background-color: #FFFFF0;
	margin: 0px;
	padding-right: 20px;
	padding-top: 3px;
	padding-bottom: 3px;
}

.vmiddle {
	vertical-align: middle;
}

.white-text {
	color: black;
}

.nav-div {
	margin-left: 20px;
}
</style>

<title>학생용 채팅 화면입니다.</title>
</head>
<body>
	<div class="form-group top-bar">
		<span class="nav-div"> <span class="vmiddle white-text">ROOM
				KEY : </span> <textarea id="roomKey" class="vmiddle" readonly rows="1"
				cols="6">${roomKey}</textarea>
		</span> <span class="nav-div"> <span class="vmiddle white-text">이름
				: </span> <textarea id="name" class="vmiddle" rows="1" cols="15" placeholder="이름입력"></textarea>
		</span>
	</div>
	<div class="container-fluid">



		<div class="row">
			<div class="col-lg-6 col-md-6 chat-basic chat-python">
				<h3 class="group-title">파이썬 화면</h3>
				<form action="">
					<div class="input-group">
						<textarea id="python-source" class="form-control" rows="12"
							placeholder="파이썬 스크립트를 붙여넣으세요."></textarea>
					</div>
					<div class="button-group">
						<button id="run" type="button" class="btn btn-primary python-btn">
							<span class="glyphicon glyphicon-play-circle"></span> 실행
						</button>
						<button id="src-send" type="button"
							class="btn btn-success python-btn">
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
				<h3 class="group-title">채팅 화면</h3>
				<form>
					<div class="input-group">
						<textarea readonly id="received-content" class="form-control"
							rows="22" placeholder="채팅 화면입니다."></textarea>
					</div>
					<div class="chat-group">
						<div class="chat-input-group">
							<textarea id="send-content" class="form-control" rows="4"
								placeholder="입력하세요."></textarea>
						</div>
						<div class="chat-button-group">
							<button id="chat-send" type="button"
								class="btn btn-success chat-btn">
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