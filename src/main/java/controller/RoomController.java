package controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import data.maps.WsHostMap;
import innerObjects.RoomKeyMaker;

@Controller //방생성 및 방참여 담당
public class RoomController {
	
	private WsHostMap wsMap;
	public void setWsHostMap(WsHostMap wsMap){
		System.out.println("controller에서 ws장착");
		this.wsMap = wsMap;
	}
		

	//방생성 매핑 : chat master 전용
	@RequestMapping("/make")
	public String makeRoom(HttpServletRequest request){
		//1.
		String tempRoomKey = RoomKeyMaker.makeRoomKey();
		while(wsMap.isRoomExist(tempRoomKey))
			tempRoomKey = RoomKeyMaker.makeRoomKey();
		
		return "redirect:"+tempRoomKey;
	}

	//방참여 매핑 : chat guest 전용 //host와 client 분기시켜야 한다. 
	@RequestMapping("/{roomKey}")
	public String joinRoom(HttpServletRequest request, @PathVariable String roomKey){
		String viewUrl = null;
		
		//2. host와 client분기
		if(!wsMap.isRoomExist(roomKey))
			viewUrl = "wshost";
		else
			viewUrl = "wsclient";
		
		return viewUrl;
	}


}
