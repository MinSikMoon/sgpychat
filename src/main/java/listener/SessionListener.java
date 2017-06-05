package listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

@Controller
public class SessionListener implements HttpSessionListener, ServletContextAware {
	//properties
	@Autowired
	private ServletContext sc;
	
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println(session.getId() + "입장");
		
		//자꾸 sc가 null exception남. 아마 스레드 타이밍 문제인듯하다. //나중에 물어보자.
		/*HashMap<String, String> testmap =  (HashMap<String,String>)sc.getAttribute("testMap");
		testmap.put(session.getId(), "1");
		System.out.println("이건 세션리스너에서~" + testmap.size());*/
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println(session.getId() + "퇴장");

	}

	public void setServletContext(ServletContext sc) {
		System.out.println("sessionlistener 오토와이어드중" + sc.hashCode());
		if(sc == null)
			System.out.println("세션리스너에서 sc가 null이다.");
		this.sc = sc;
	}

}
