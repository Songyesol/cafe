package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getEmpListTemp")
public class GetEmpListUsingTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GetEmpListUsingTemp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDAO dao = new EmpDAO();
		List<EmployeeVO> list = dao.getEmpList();
		
		String json = "[";
		int cnt =1;
		for(EmployeeVO emp:list) {
			json += "{";
			json += "\"empId\":\""+ emp.getEmployeeId() + "\"";
			json += ",\"firstname\":\""+ emp.getFirstName() + "\"";
			json += ",\"lastName\":\""+ emp.getLastName() + "\"";
			json += ",\"email\":\""+ emp.getEmail() + "\"";
			json += ",\"hireDate\":\""+emp.getHireDate() + "\"";
			json += ",\"salary\":\""+ emp.getSalary() + "\"";
			json += "}";
			if(list.size() != cnt++) {
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
