package jcafe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetProductSurv")
public class GetProductSurv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetProductSurv() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String item = request.getParameter("item_no");
		ProductVO vo =new ProductVO();
		vo.setItemNo(item);
		
		ProductDAO dao = new ProductDAO();
		ProductVO result = dao.getProduct(vo);
		
		String json = "{";
		json += "\"itemNo\":\""+result.getItemNo()+"\"";
		json += ",\"item\":\""+result.getItem()+"\"";
		json += ",\"price\":\""+result.getPrice()+"\"";
		json += ",\"link\":\""+result.getLink()+"\"";
		json += ",\"category\":\""+result.getCategory()+"\"";
		json += ",\"content\":\""+result.getContent()+"\"";
		json += ",\"likeIt\":\""+result.getLikeIt()+"\"";
		json += ",\"alt\":\""+result.getAlt()+"\"";
		json += ",\"image\":\""+result.getImage()+"\"";
		json += "}";
		

		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		doGet(request, response);
	}

}
