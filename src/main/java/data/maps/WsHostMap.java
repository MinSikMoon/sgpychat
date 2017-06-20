package data.maps;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WsHostMap {
	//properties
	private Map<String, String> hostMap;
	private Map<String, WebSocketSession> sessionMap;
	
	//constructor
	public WsHostMap(){
		hostMap = new HashMap<String, String>();
		sessionMap = new HashMap<String, WebSocketSession>();
	}
	
	//get/set
	public void setRoomHost(String roomKey, String id){
		hostMap.put(roomKey, id);
	}
	public void setSession(String id, WebSocketSession session){
		sessionMap.put(id, session);
	}
	
	public WebSocketSession getRoomHostSession(String roomKey){
		String hostId = null;
		WebSocketSession resultSession = null;
		if(hostMap.containsValue(roomKey)){
			hostId = hostMap.get(roomKey);
			resultSession = sessionMap.get(hostId);
		}
		return resultSession;
	}
	
	public WebSocketSession getSession(String id){
		WebSocketSession resultSession = null;
		if(sessionMap.containsKey(id))
			resultSession = sessionMap.get(id);
		return resultSession;
	}
	
	
}
