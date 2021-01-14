package jcafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	Connection conn=null;
	public ProductDAO() {
			try {
				String user = "hr";
				String pw = "hr";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";

				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, pw);

				System.out.println("Database에 연결되었습니다.\n");

			} catch (ClassNotFoundException cnfe) {
				System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
			} catch (SQLException sqle) {
				System.out.println("DB 접속실패 : " + sqle.toString());
			} catch (Exception e) {
				System.out.println("Unkonwn error");
				e.printStackTrace();
			}

		} //end of 생성자
	
	public ProductVO getProduct(ProductVO vo) {
		String sql = "select * from product where item_no = ? ";
		ProductVO v = null;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getItemNo());
			
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				v = new ProductVO();
				v.setAlt(rs.getString("alt"));
				v.setCategory(rs.getString("category"));
				v.setContent(rs.getString("content"));
				v.setImage(rs.getString("image"));
				v.setItem(rs.getString("item"));
				v.setItemNo(rs.getString("itemNo"));
				v.setLikeIt(rs.getInt("likeIt"));
				v.setLink(rs.getString("link"));
				v.setPrice(rs.getInt("price"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return v;
		
	}
	
	public List<ProductVO> getProductList(){
		String sql = "select * from product order by 1";
		List<ProductVO> list = new ArrayList<>();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) { //next라는 메소드가 rs내의 데이터를 꺼내옴. 가져올 데이터가 있으면 true 없으면 false
				ProductVO vo = new ProductVO();
				vo.setItemNo(rs.getString("item_no"));
				vo.setItem(rs.getString("item"));
				vo.setCategory(rs.getString("category"));
				vo.setPrice(rs.getInt("price"));
				vo.setLink(rs.getString("Link"));
				vo.setContent(rs.getString("content"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setAlt(rs.getString("alt"));
				vo.setImage(rs.getString("image"));
				 list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}//end of getProductList()
	
	public void insertProduct(ProductVO vo) {
		String sql = "insert into product values (?,?,?,?,'item.jsp',?,?,?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getItemNo());
			psmt.setString(2, vo.getItem());
			psmt.setString(3, vo.getCategory());
			psmt.setInt(4, vo.getPrice());
			psmt.setString(5, vo.getContent());
			psmt.setInt(6, vo.getLikeIt());
			psmt.setString(7, vo.getItem());
			psmt.setString(8, vo.getImage());
			
			int r = psmt.executeUpdate();
			System.out.println(r+"건 입력완료");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} //end of insertProduct();
	
}
	

