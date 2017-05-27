package data.maps;

import java.util.HashMap;

public class SessionNumMap {
	//properties
	HashMap<String, Integer> map = null;
	
	//constructor
	public SessionNumMap(){
		map = new HashMap<String, Integer>();
	}
	
	//method 1 : add : 새로운 방 key로 하고 참여인원 1로 초기화
	public void addNewRoom(String roomKey){
		if(!map.containsKey(roomKey)){
			map.put(roomKey, 1);
		}
	}
	//method 2 : removeOne : 해당 방의 인원 1 감소 & 증가
	public void addGuest(String roomKey){
		if(map.containsKey(roomKey)){
			map.put(roomKey, (map.get(roomKey)+1));
		}
	}
	// 방인원이 1감소한 값을 리턴한다. -1이면 room이 없다는 뜻. 그리고 0이면 나중에 따로 처리할 로직들을 연결시켜준다. 
	public int removeGuest(String roomKey){ 
		int tempCnt = -1;
		if(map.containsKey(roomKey)){
			tempCnt = map.get(roomKey)-1;
			map.put(roomKey, tempCnt);
		}
		return tempCnt;
	}
}
