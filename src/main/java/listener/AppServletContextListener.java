package listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AppServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent contextEvent) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent event) {
		//여기서 서블릿 컨텍스트 준비시켜준다. 
		ServletContext sc = event.getServletContext();
		//servlet context에 데이터 맵을 넣는다. 
		HashMap<String, String> testMap = new HashMap<String, String>();
		//sc에 저장
		sc.setAttribute("testMap", testMap);
		System.out.println("서블릿 컨텍스트 장착완료~~");
	}

}
