package data.maps;

import java.util.HashMap;

//방의 호스트가 누구인지 나타내주는 맵
//<roomKey, jid>
public class HostMap {
	//properties
	HashMap<String, String> map;
	
	//constructor
	public HostMap(){
		map = new HashMap<String, String>();
	}
	
	//methods
	public void addRoomHost(String roomKey, String hostJid){
		if(!map.containsKey(roomKey))
			map.put(roomKey, hostJid);
	}
	public void removeRoomHost(String roomKey){
		if(map.containsKey(roomKey))
			map.remove(roomKey);
	}
	public String getRoomHost(String roomKey){
		return map.get(roomKey);
	}
}
