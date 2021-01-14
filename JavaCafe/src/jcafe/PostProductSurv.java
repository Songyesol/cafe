package jcafe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PostProduct")
public class PostProductSurv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PostProductSurv() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String itemNo=request.getParameter("itemNo");
		String item = request.getParameter("item");
		String category = request.getParameter("category");
		String price = request.getParameter("price");
		price = price ==null? "0": price;
		int priceInt = Integer.parseInt(price);
		String content = request.getParameter("content");
		String likeIt = request.getParameter("likeIt");
		likeIt = likeIt ==null? "0": likeIt;
		int like=Integer.parseInt(likeIt);
		String image = request.getParameter("image");
		
		
		 System.out.println(itemNo); 
		 System.out.println(item);
		 System.out.println(category); 
		 System.out.println(priceInt);
		 System.out.println(content); 
		 System.out.println(like);
		 System.out.println(image);
		
		  ProductVO vo = new ProductVO(); 
		  vo.setItemNo(itemNo); 
		  vo.setItem(item);
		  vo.setCategory(category); 
		  vo.setContent(content); 
		  vo.setPrice(priceInt);
		  vo.setLikeIt(like); 
		  vo.setImage(image);
		  
		  ProductDAO dao=new ProductDAO();
		  dao.insertProduct(vo);
		  
		  String script = "<script>location.href='jcafe/cafeList.html'</script>";
		  response.getWriter().append(script);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
