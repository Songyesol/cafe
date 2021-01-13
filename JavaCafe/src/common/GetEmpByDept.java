package common;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetMembersByDept")
public class GetEmpByDept extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetEmpByDept() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDAO dao = new EmpDAO();
		Map<String, Integer> map = dao.getMemberByDept();
		Set<String> keySet = map.keySet();
		
		String json = "[";
		int cnt = 1;
		for(String key : keySet) {
			System.out.println(key+", "+map.get(key));
			json += "{";
			json += "\"" + key + "\":" + map.get(key);
			json += "}";
			if(map.size() != cnt++) {
				json += ",";
			} //json은 {key:value} 형식으로 받아와야한다.
		}
		
		json += "]";
		
		response.getWriter().append(json);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
