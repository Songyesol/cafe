package common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getSchedule")
public class getScheduleSurvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public getScheduleSurvlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		
		EmpDAO dao = new EmpDAO();
		List<ScheduleVO> list = dao.getScheduleList();
		
		String json = "[";
		int cnt =1;
		for(ScheduleVO vo:list) {
			json += "{";
			json += "\"title\":\""+vo.getTitle()+"\"";
			json += ",\"start\":\""+vo.getStartDate()+"\"";
			json += ",\"end\":\""+vo.getEndDate()+"\"";
			json += ",\"url\":\""+vo.getUrl()+"\"";
			json += "}";
			
			if(list.size()!= cnt++) {
				json += ",";
			}
		}
		json += "]";
		response.getWriter().append(json);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
