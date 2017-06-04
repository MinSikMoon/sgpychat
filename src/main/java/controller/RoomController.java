package controller;


import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import innerObjects.RoomKeyMaker;

@Controller //방생성 및 방참여 담당
public class RoomController implements ServletContextAware{
	//properties
	@Autowired
	private ServletContext sc;
	//setter
	public void setServletContext(ServletContext sc){
		this.sc = sc;
	}
	//방생성 매핑 : chat master 전용
	@RequestMapping("/make")
	public String makeRoom(HttpServletRequest request){
		//1. 6자리 key 생성
		String tempRoomKey = RoomKeyMaker.makeRoomKey();
		if(sc == null){
			System.out.println("sc가 널이네");
		}
		//2. tempRoomKey가 dialogueMap에 이미 존재하는지 아닌지 조사한다. //그 전에 dialogueMap이 존재해야함.
		//일단 테스트 용으로 sc에 넣는 걸로 해보자.
		//sc 얻어오기
		HashMap<String, String> testmap =  (HashMap<String,String>)sc.getAttribute("testMap");
		testmap.put(tempRoomKey, "1");
		System.out.println(testmap.size());
		
		//System.out.println(request.getSession().getId());
		return "index";
	}
	
	//방참여 매핑 : chat guest 전용
	@RequestMapping("/{roomKey}")
	public String joinRoom(@PathVariable String roomKey){
		System.out.println(roomKey);
		return "index";
	}
}
