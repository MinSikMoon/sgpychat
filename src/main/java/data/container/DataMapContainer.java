package data.container;

import data.maps.DialoguesMap;
import data.maps.HostMap;
import data.maps.RoomKeysMap;
import data.maps.SessionIdsMap;
import data.maps.SessionNumMap;

//sgpychat에서 쓰일 map들을 총괄 관리할 객체임 : 이게 sc에 들어감.
public class DataMapContainer {
	//properties //언젠가는 의존성 주입으로 바꿔야 할 듯.
	//key가 roomkey인 map들
	private HostMap hostMap; //1. 방장
	private DialoguesMap dialoguesMap; //2. 대화 목록
	private SessionIdsMap sessionIdsMap; //3. 참여 세션 현황
	private SessionNumMap sessionNumMap; //4. 참여인원
	//key가 jid인 map
	private RoomKeysMap roomKeysMap; //5. jid 당 참여중인 방keys
	
	//constructor
	public DataMapContainer(){
		hostMap = new HostMap();
		dialoguesMap = new DialoguesMap();
		sessionIdsMap = new SessionIdsMap();
		sessionNumMap = new SessionNumMap();
		roomKeysMap = new RoomKeysMap();
	}
	
	//method1 : make 요청이 와서 방을 만들고 jid 관리해주는것
	public void makeNewRoom(String roomKey, String hostJid){
		dialoguesMap.makeNewRoom(roomKey);
		hostMap.addRoomHost(roomKey, hostJid);
		sessionIdsMap.addSessionId(roomKey, hostJid);
		sessionNumMap.addNewRoom(roomKey);
		roomKeysMap.addNewRoomKey(hostJid, roomKey);
	}
	//methods
	//정리 : 
	//1. make 리퀘스트가 들어오면 먼저 리퀘스트에서 jid를 꺼낸다. : 일단 방을 만들어야 함.
	//2. 존재하는 jid인가 새로운 jid인가? 
	//		->존재하면 방참여 리스트에 매달아주고, 아니면 새롭게 생성해준다.
	//3. 방을 만들고 참여자1로 초기화 해준다. 
	//4. 대화 리스트도 만들어준다. 
	
	
	
	//1. Dialogues 대화 관련
	public boolean isRoomExist(String roomKey){
		return dialoguesMap.isExist(roomKey);
	}
	public boolean addRoom(String roomKey){
		return dialoguesMap.makeNewRoom(roomKey);
	}
	public boolean removeRoom(String roomKey){
		return dialoguesMap.removeRoom(roomKey);
	}
	//2. roomKeys 관련 : 한 세션이 어떤 대화방에 참여하고 있나
	public int getSessionCnt(){
		return roomKeysMap.getSessionNum();
	}
	public void addRoomKey(String jid, String roomKey){
		roomKeysMap.addNewRoomKey(jid, roomKey);
	}
	public void removeRoomKey(String jid, String roomKey){
		roomKeysMap.removeRoomKey(jid, roomKey);
	}
	public int getRoomKeyCnt(String jid){
		return roomKeysMap.getRoomKeyCnt(jid);
	}
	//3. sessionNum 관련 : 어떤 방에 몇명이 참여중인가
	public void addNewRoomGuest(String roomKey){
		sessionNumMap.addNewRoom(roomKey);
	}
	public void addGuest(String roomKey){
		sessionNumMap.addGuest(roomKey);
	}
	public void removeGuest(String roomKey){
		sessionNumMap.removeGuest(roomKey);
	}
	
	//toString :
	@Override
	public String toString(){
		return dialoguesMap.toString();
	}
}
