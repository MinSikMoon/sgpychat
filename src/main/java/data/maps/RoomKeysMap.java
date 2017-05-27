package data.maps;

import java.util.HashMap;

import data.models.RoomKeys;

public class RoomKeysMap {
	//properties
	HashMap<String, RoomKeys> map = null;
	//constructor
	public RoomKeysMap(){
		map = new HashMap<String, RoomKeys>();
	}

	//method 1 : add
	public void addNewRoomKey(String jid, String roomKey){
		//1. jid가 존재하는 것이라면
		if(map.containsKey(jid)){
			RoomKeys tempRk = map.get(jid);
			tempRk.addRoomKey(roomKey);
		}else{ //2. 처음 들어오는 jid라면 //생성과 동시에 넣어준다. 
			map.put(jid, new RoomKeys(roomKey));
		}
	}

	//method 2 : remove
	public void removeRoomKey(String jid, String roomKey){
		//1. jid와 roomKey가 존재할 때만 지워줘야 겠지.
		if(map.containsKey(jid)){
			RoomKeys tempRk = map.get(jid);
			if(tempRk.isExist(roomKey)){
				tempRk.removeRoomKey(roomKey);
			}
		}
	}
	
	//method 3 : 현재 몇개의 방에 참여 중인지 리턴
	public int getRoomKeyCnt(String jid){
		RoomKeys tempRk = map.get(jid);
		return tempRk.getCnt();
	}



}
