package data.models;

import java.util.TreeSet;

public class RoomKeys {
	//properties
	private TreeSet<String> roomKeys = null;
	//constructor
	public RoomKeys(){
		roomKeys = new TreeSet<String>();
	}
	public RoomKeys(String roomKey){
		TreeSet<String> temp = new TreeSet<String>();
		temp.add(roomKey);
		roomKeys = temp;
	}
	
	//method 1 : add roomKeys
	public void addRoomKey(String roomKey){
		if(!roomKeys.contains(roomKey)){
			roomKeys.add(roomKey);
		}
	}
	//method 2 : remove roomKey
	public void removeRoomKey(String roomKey){
		if(roomKeys.contains(roomKey)){
			roomKeys.remove(roomKey);
		}
	}
	
	//method 3 : isExist
	public boolean isExist(String roomKey){
		return roomKeys.contains(roomKey);
	}
	
	//method 4 : getCnt
	public int getCnt(){
		return roomKeys.size();
	}
}
