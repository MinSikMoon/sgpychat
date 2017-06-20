package controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	//웹소켓 테스트
	@RequestMapping("/wstest")
	public String wsTest(){
		return "wsclient_test";
	}
	

	//방생성 매핑 : chat master 전용
	@RequestMapping("/make")
	public String makeRoom(HttpServletRequest request){
		//1.sessionJid & dmc 얻어오기
		String tempJid = request.getSession().getId();
		DataMapContainer dmc = (DataMapContainer)sc.getAttribute("dmc");
		//2. 존재하지 않는 roomKey를 만든다.
		String tempRoomKey = RoomKeyMaker.makeRoomKey();
		while(dmc.isRoomExist(tempRoomKey))
			tempRoomKey = RoomKeyMaker.makeRoomKey();
		//3. dmc에서 새로운 방 생성한다. 
		dmc.makeNewRoom(tempRoomKey, tempJid);
		System.out.println(dmc);


		return "redirect:"+tempRoomKey;
	}

	//방참여 매핑 : chat guest 전용 //host와 client 분기시켜야 한다. 
	@RequestMapping("/{roomKey}")
	public String joinRoom(HttpServletRequest request, @PathVariable String roomKey){
		//1.sessionJid & dmc 얻어오기
		String tempJid = request.getSession().getId();
		DataMapContainer dmc = (DataMapContainer)sc.getAttribute("dmc");
		String viewUrl = null;
		
		//2. host와 client분기
		if(dmc.isHost(roomKey, tempJid))
			viewUrl = "wshost";
		else
			viewUrl = "wsclient";
		
		System.out.println(roomKey);
		return viewUrl;
	}

	//방나가기 매핑 : 
	/*@RequestMapping("/exit/{roomKey}")
	@ResponseBody
	public String exitRoom(HttpServletRequest request, @PathVariable String roomKey){
		String tempJid = request.getSession().getId();
		System.out.println(tempJid + "가 "+roomKey+ "를 나갑니다.");
		//request.getSession().invalidate();
		return "";
	}*/


}
