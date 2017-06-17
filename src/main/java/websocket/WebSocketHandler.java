package websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {
	
	//1. connection established
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println(session.getId() + "웹소켓 연결됨");
	}
	//2. text message
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		System.out.println(session.getId() + "로 부터 " + message.getPayload());
	}
	//3. connection closed
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		System.out.println(session.getId() + " 연결 종료~");
	}
}