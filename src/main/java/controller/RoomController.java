package controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import data.container.DataMapContainer;
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
		//1. 6자리 key 생성 & sessionJid 얻어오기
		String tempRoomKey = RoomKeyMaker.makeRoomKey();
		String tempJid = request.getSession().getId();
		//2. dmc에서 새로운 방 생성한다. 
		DataMapContainer dmc = (DataMapContainer)sc.getAttribute("dmc");
		dmc.makeNewRoom(tempRoomKey, tempJid);
		System.out.println(dmc);
		return "redirect:"+tempRoomKey;
		
		
	}
	
	//방참여 매핑 : chat guest 전용
	@RequestMapping("/{roomKey}")
	public String joinRoom(@PathVariable String roomKey){
		System.out.println(roomKey);
		return "client";
	}
}
