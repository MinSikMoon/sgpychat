package data.maps;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import data.models.Dialogues;

//<roomKey, Dialogues>
public class DialoguesMap {
	//properties
	HashMap<String, Dialogues> map = null;
	
	//constructor
	public DialoguesMap(){
		map = new HashMap<String, Dialogues>();
	}
	public DialoguesMap(String roomKey){
		this();
		map.put(roomKey, new Dialogues(roomKey + "방이 개설되었습니다."));
	}
	
	//method1 : key있는지 없는지 확인
	public boolean isExist(String roomKey){
		return map.containsKey(roomKey);
	}
	
	//method2 : key를 입력하고 방이 있다면 방을 만든다.
	public boolean makeNewRoom(String roomKey){
		boolean result = false;
		if(!map.containsKey(roomKey)){
			map.put(roomKey, new Dialogues(roomKey + "방이 개설되었습니다."));
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
	
	//method4 : 대화입력
	
	
	
	//toString :
	@Override
	public String toString(){
		String result = "==대화 목록==\n";
		Set<String> keySet = map.keySet();
		
		for(String key : keySet){
			result += ("->key: "+key + "\n");
			Dialogues dialogue = map.get(key);
			List<String> temp = dialogue.getDialogues();
			for(String dl : temp){
				result += (dl+"\n");
			}
		}
		result += "===대화 목록 끝== \n";
		return result;
	}
	
	
}
