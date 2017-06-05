package data.maps;

import java.util.HashMap;
import data.models.SessionIds;

//각 방에 어떤 참여자들이 들어있나? : 0번 인덱스가 host다.
public class SessionIdsMap {
	//properties : <roomKey, SessionIds>
	HashMap<String, SessionIds> map;
	//constructor
	public SessionIdsMap(){
		map = new HashMap<String, SessionIds>();
	}
	public SessionIdsMap(String roomKey, String hostJid){
		this();
		map.put(roomKey, new SessionIds(hostJid));
	}
	//methods
	public SessionIds getSessionIds(String roomKey){
		SessionIds result = null;
		if(map.containsKey(roomKey))
			result = map.get(roomKey);
		return result;
	}
	public void addSessionId(String roomKey, String jid){
		if(map.containsKey(roomKey))
			map.get(roomKey).addJid(jid);
	}
	
}
