package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {
	private Connection conn;

	public EmpDAO() {
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
	
	public List<ScheduleVO> getScheduleList() {
		String sql = "select * from schedules";
		List<ScheduleVO> list = new ArrayList<>();
		  
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs;
			rs = psmt.executeQuery();
			while(rs.next()) {
				ScheduleVO vo=new ScheduleVO();
				vo.setTitle(rs.getString("title"));
				vo.setStartDate(rs.getString("start_date"));
				vo.setEndDate(rs.getString("end_date"));
				vo.setUrl(rs.getString("url"));
				list.add(vo);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}//달력 select
	
	public void insertSchedule(ScheduleVO vo) {
		String sql = "insert into schedules values(?,?,?,?)"; 
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getStartDate());
			psmt.setString(3, vo.getEndDate());
			psmt.setString(4, vo.getUrl());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력완료");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// 달력 insert 
	
	
	public Map<String, Integer> getMemberByDept(){
		String sql = "select department_name, count(*) from employees e, departments d "
				+ "where e.department_id = d.department_id group by department_name";
		Map<String, Integer> map = new HashMap<>();
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs= psmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
				
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
		return map;
		
	}
	
/////////////////// SELECT ////////////////////
	
	public List<EmployeeVO> getEmpList() {
		String sql = "select * from emp_temp order by 1 desc";
		List<EmployeeVO> list = new ArrayList<>();
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setEmployeeId(rs.getInt("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setJobId(rs.getString("job_id"));
				vo.setSalary(rs.getInt("salary"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}// end of getEmpList()
	
/////////////////// DELETE ////////////////////
	public boolean deleteEmp(EmployeeVO vo) {
		String sql = "delete from emp_temp where employee_id = ?";
		int r=0;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1,vo.getEmployeeId());
			
			r=psmt.executeUpdate();
			System.out.println(r + "건이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return r==1 ? true:false;
	}// end of deleteEmp()
	
/////////////////// INSERT ////////////////////
	public EmployeeVO insertEmp(EmployeeVO vo) {
		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "select * from emp_temp where employee_id = ?";
		String sql = "insert into emp_temp (employee_id,first_name, last_name, email, hire_date, job_id) "
				+ "values(?,?,?,?,sysdate,?)";
		int resultTime= 0;
		String newSeq = null;
		EmployeeVO newVo = new EmployeeVO(); 
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql1);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				newSeq = rs.getString(1);//1은 쿼리한 첫번째 컬럼을 의미 
			} // 시퀀스 번호 조회한 것을 newSeq변수에 담음
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, newSeq);
			psmt.setString(2,vo.getFirstName());
			psmt.setString(3, vo.getLastName());
			psmt.setString(4, vo.getEmail());
			psmt.setString(5, vo.getJobId());
			resultTime= psmt.executeUpdate();
			System.out.println(resultTime + "건이 입력되었습니다.");
			
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, newSeq);
			rs = psmt.executeQuery();
			if(rs.next()) {
				newVo.setEmail(rs.getString("email"));
				newVo.setEmployeeId(rs.getInt("employee_id"));
				newVo.setFirstName(rs.getString("first_name"));
				newVo.setLastName(rs.getString("last_name"));
				newVo.setJobId(rs.getString("job_id"));
				newVo.setHireDate(rs.getString("hire_date"));
				newVo.setSalary(rs.getInt("salary"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newVo;
	} //end of insertEmp()
	
/////////////////// UPDATE ////////////////////
	public EmployeeVO updateEmp(EmployeeVO vo) { 
	String sql = "update emp_temp set first_name = ?, last_name = ?, email = ?, job_id=? where employee_id = ?"; 
	int r = 0;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getFirstName());
			psmt.setString(2,vo.getLastName());
			psmt.setString(3,vo.getEmail());
			psmt.setString(4,vo.getJobId());
			psmt.setInt(5,vo.getEmployeeId());
			
			r = psmt.executeUpdate();
			System.out.println(r + "건이 업데이트 되었습니다");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;	
	} //end of updateEmp()
	
	
}
	