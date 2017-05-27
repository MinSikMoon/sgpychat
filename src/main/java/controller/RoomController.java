package controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import innerObjects.RoomKeyMaker;

@Controller //방생성 및 방참여 담당
public class RoomController {
	//방생성 매핑 : chat master 전용
	@RequestMapping("/make")
	public String makeRoom(HttpServletRequest request){
		//1. 6자리 key 생성
		String tempRoomKey = RoomKeyMaker.makeRoomKey();
		//2. tempRoomKey로 대화맵 생성
		
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
