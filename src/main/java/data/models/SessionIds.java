package data.models;

import java.util.LinkedList;
import java.util.List;


//jid들을 리스트 구조로 저장해주는 자료구조
public class SessionIds {
	//properties
	private List<String> jids = null;
	//constructor
	public SessionIds(){
		this.jids = new LinkedList<String>();
	}
	public SessionIds(String hostJid){
		this.jids = new LinkedList<String>();
		this.jids.add(hostJid); 
	}
	//method
	public boolean isExist(String jid){
		return jids.contains(jid);
	}
	public int getCnt(){
		return jids.size();
	}
	public void addJid(String jid){
		if(!jids.contains(jid))
			jids.add(jid);
	}
	public void removeJid(String jid){
		if(jids.contains(jid))
			jids.remove(jid);
	}
}
