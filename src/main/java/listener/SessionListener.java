package listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Controller;

@Controller
public class SessionListener implements HttpSessionListener {
	private Object getAttributeFromSc(HttpSessionEvent event, String key){
		return event.getSession().getServletContext().getAttribute(key);
	}
	
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println(session.getId() + "입장");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println(session.getId() + "퇴장");
		//세션 파괴
		session.invalidate();
	}
}
