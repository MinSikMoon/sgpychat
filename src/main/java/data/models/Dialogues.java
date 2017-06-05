package data.models;

import java.util.LinkedList;
import java.util.List;

//대화 객체
public class Dialogues {
	//Properties
	private List<String> dialogues = null;
	
	//Constructor
	public Dialogues(){
		dialogues = new LinkedList<String>();
	}
	public Dialogues(String greetingMessage){
		this();
		dialogues.add(greetingMessage);
	}
	
	//대화 add
	public void add(String newDialogue){
		dialogues.add(newDialogue);
	}
	
	//대화 갯수 반환
	public int size(){
		return dialogues.size();
	}
	
	//대화 리스트 반환
	public List<String> getDialogues(){
		return dialogues;
	}
}
