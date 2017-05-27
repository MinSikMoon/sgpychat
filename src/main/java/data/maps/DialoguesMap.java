package data.maps;

import java.util.HashMap;

import data.models.Dialogues;

public class DialoguesMap {
	//properties
	HashMap<String, Dialogues> map = null;
	
	//constructor
	public DialoguesMap(){
		map = new HashMap<String, Dialogues>();
	}
	
	//method1 : key있는지 없는지 확인
	public boolean isExist(String roomKey){
		return map.containsKey(roomKey);
	}
	
	//method2 : key를 입력하고 방이 있다면 방을 만든다.
	public boolean makeNewRoom(String roomKey){
		boolean result = false;
		if(!map.containsKey(roomKey)){
			map.put(roomKey, new Dialogues());
			result = true;
		}
		return result;
	}
	
	//method3 : key로 된 방을 없앤다.
	public boolean removeRoom(String roomKey){
		boolean result = false;
		if(map.containsKey(roomKey)){
			map.remove(roomKey);
			result = true;
		}
		return result;
	}
}
