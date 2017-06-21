package websocket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import data.maps.WsHostMap;

public class WebSocketHandler extends TextWebSocketHandler {
	//properties : 웹 소켓들을 저장하는 데이터 구조 필요
	private WsHostMap map = null;
	private JSONParser jsonParser = null;
	
	//setter
	public void setWsHostMap(WsHostMap map){
		System.out.println("hander에서 ws장착");
		this.map = map;
	}
	public void setJSONParser(JSONParser jsonParser){
		System.out.println("파서 장착!!");
		this.jsonParser = jsonParser;
	}
	
	//inner method
	private String getMessage(String jsonMsg, String key) throws Exception{
		JSONObject jsonObj = (JSONObject)jsonParser.parse(jsonMsg);
		return (String)jsonObj.get(key);
	}
	
	//methods
	//1. connection established
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println(session.getRemoteAddress().getHostName() + "웹소켓 연결됨");
	}
	//2. text message
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		String sessionId = session.getId();
		String jsonMsg = message.getPayload();
		String roomKey = getMessage(jsonMsg, "roomKey");
		//type이 make인지 message인지 부터 파악
		if(getMessage(jsonMsg, "type").equals("make")){
			map.setRoomHost(roomKey, sessionId);
		}else{
			//메시지라면 host에서 온건지 client에서 온건지 파악한다.
			
		}
		
		
		
	}
	//3. connection closed
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		System.out.println(session.getId() + " 연결 종료~");
	}


}
