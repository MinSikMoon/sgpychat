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
		var PK = 0;
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
		//메시지 전송 테스트
		$("#send-btn").click(function() {
			//입력내용 전송하고
			var msg = $("#response-msg").val();
			$("#test-output").html(msg);
			//다시 지워주기
			$("#response-msg").val('');
		})

		//엘리먼트 생성 테스트
		$("#test-btn").click(function() {
			$("#chat-list").append($(this).attr("value"));
		})
		
		/* 웹소켓 관련 */
		var webSocket = new WebSocket("ws://localhost:8080/sgpychat/testing/ws");
		var date = new Date();
		var time = date.toLocaleDateString() + ", " + date.toLocaleTimeString();
		//웹 소켓이 연결되었을 때 호출되는 이벤트
		webSocket.onopen = function(message) {
			//생성과 동시에 roomKey관련 메시지를 보낸다.
			var msg = {
				type : "make",
				roomKey : $("#roomKey").val(),
			};
			webSocket.send(JSON.stringify(msg));
		};
		//웹 소켓이 닫혔을 때 호출되는 이벤트
		webSocket.onclose = function(message) {
			$("#received-content").val("Server Disconnect...\n");
		};
		//웹 소켓이 에러가 났을 때 호출되는 이벤트
		webSocket.onerror = function(message) {
			$("#received-content").val("error...\n")
		};
		//웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
		webSocket.onmessage = function(message) {
			var obj = JSON.parse(message.data);
			var contentType = obj.contentType;
			var addingElem = null;
			var chatElemPk = "chatElem"+(PK++);
			 if(contentType == "chat"){
				addingElem = $("<button class='btn list-elem btn-warning btn-lg btn-block overflow-hidden chat-btn' data-toggle='modal' data-target='#chatModal'></button>")
				.append("<span class='label label-danger cnt-badge'></span>")
				.append("<span class='label label-success name-badge'>"+obj.name+"</span>")
				.append(obj.content);
				addingElem.on('click', function(){
					$('#chat-modal-name').text(obj.name);
					$('#chat-modal-content').text(obj.content);
					$('#client-id').text(obj.clientId);
					$('#chat-remove-btn').attr("target-pk",chatElemPk);
				});
				addingElem.attr('id',chatElemPk);
				
								
			}else{
				addingElem = $("<button class='btn list-elem btn-primary btn-lg btn-block overflow-hidden src-btn' data-toggle='modal' data-target='#srcModal'>"+obj.content+"</button>")
				.append("<span class='label label-danger cnt-badge'>"+$('.list-elem').length+"</span>")
				.append("<span class='label label-success name-badge'>"+obj.name+"</span>");
			}
			$("#chat-list").append(addingElem); 
			idxReset();
			//$("#chat-list").append("<button class=\"btn btn-primary btn-lg btn-block overflow-hidden src-btn\">"+obj.content+"</button>");
		};
		//Send 버튼을 누르면 실행되는 함수 : 메시지를 json으로 만든다. 
		/* $(".chat-btn").click(function(){
			$('#chat-modal-content').text($(this).find('.name-badge').text());
		}) */
		
		//인덱스 리셋 함수
		idxReset = function(){
			$('.cnt-badge').each(function(index){
				$(this).text(index);
			});
		}
		
		$("#chat-send").click(function() {
			var msg = {
				type : "host",
				content : $("#send-content").val(),
				date : Date.now()
			};
			//웹소켓으로 textMessage객체의 값을 보낸다.
			webSocket.send(JSON.stringify(msg));
			//textMessage객체의 값 초기화
			$("#send-content").val('');
		})
		$("#chat-remove-btn").click(function() {
			var targetId ="#"+$(this).attr("target-pk");
			$(targetId).remove();
			idxReset();
		})
		//웹소켓 종료
		function disconnect() {
			webSocket.close();
		}
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

.right-text-align {
	text-align: right;
}

.name-badge {
	float: left;
	margin-right: 5px;
}

.cnt-badge {
	float: left;
	margin-right: 2px;
}

.overflow-hidden {
	text-overflow: ellipsis;
}

.host-chat-monitor {
	
}

.host-chat-list {
	
}

.chat-btn {
	
}

.src-btn {
	
}

.src-modal-header {
	color: white;
	background-color: #337ab7;
}

.chat-modal-header {
	color: white;
	background-color: #ff9800;
}

.blue-color {
	background-color: #337ab7;
}
</style>

<title>교수자 화면 목업</title>
</head>
<!-- =====================================BODY=================================== -->
<body>
	<div class="form-group top-bar">
		<span class="nav-div"> <span class="vmiddle white-text">ROOM
				KEY : </span> <textarea id="roomKey" class="vmiddle" readonly rows="1" cols="6">${roomKey}</textarea>
		</span> <span class="nav-div"> <span class="vmiddle white-text">교수자용
				화면 </span></span>
	</div>


	<div class="container">
		<div class="row">
			<div id="chat-list"
				class="host-chat-list well well-lg col-sm-8 col-md-8 col-lg-8 col-sm-offset-2 col-md-offset-2 col-lg-offset-2">
				<div class="panel-group">
					<div class="panel panel-default">
						<button class="btn btn-lg" id="test-btn" value="wowowow  hahahaha">테스트입니다.</button>
						<div class="panel-body right-text-align">
							소스 <span class="badge">1</span> 채팅 <span class="badge">2</span>
						</div>
						
					</div>
				</div>
				<button type="button"
					class="btn btn-primary btn-lg btn-block overflow-hidden src-btn"
					data-toggle="modal" data-target="#srcModal">
					<span class="label label-danger cnt-badge">1</span> <span
						class="label label-success name-badge">문민식</span>
					글내용블라블랑ㄴㄻㄴㅇㄹasdfasdfasdfsadfsadfsadfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdasfas
				</button>
				<button type="button" class="btn btn-warning btn-lg btn-block chat-btn"
					data-toggle="modal" data-target="#chatModal">
					<span class="label label-danger cnt-badge">2</span> <span
						class="label label-success name-badge">김철수</span>Button 2
				</button>
			</div>
		</div>
	</div>

	<!-- Modal-src -->
	<div id="srcModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header src-modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						파이썬 소스코드 from <span class="label label-success">문민식</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="input-group">
						<textarea id="python-source" class="form-control" rows="15"
							placeholder="파이썬 스크립트를 붙여넣으세요."></textarea>
					</div>

					<div class="input-group">
						<textarea readonly id="python-output" class="form-control"
							rows="7" placeholder="결과화면입니다."></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button id="run" type="button" class="btn btn-primary python-btn">RUN</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal-chat -->
	<div id="chatModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header chat-modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						채팅내용 from <span id='chat-modal-name' class="label label-success">문민식</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="chat-group">
						<div class="input-group">
							<textarea readonly id="chat-modal-content" class="form-control" rows="15"
								placeholder="채팅 화면입니다."></textarea>
						</div>


						<div class="chat-input-group">
							<input id="client-id" type="hidden"></input>
							<input id="elem-pk" type="hidden"></input>
							<textarea id="response-msg" class="form-control" rows="4"
								placeholder="입력하세요."></textarea>
						</div>
						<div class="chat-button-group">
							<button id="send-btn" type="button"
								class="btn btn-success chat-btn" data-dismiss="modal">
								<span class="glyphicon glyphicon-send"></span> 전송
							</button>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="chat-remove-btn" type="button" class="btn btn-danger python-btn" target-pk="" data-dismiss="modal">삭제</button>
				</div>
			</div>

		</div>
	</div>
	<p id="test-output"></p>
</body>





















</html>