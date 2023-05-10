package com.shinhan.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.vo.EmpVO;

@Repository // Repository: Component + DAO(date access object)
public class X_EmpDAO {
	@Autowired // type이 같으면 injection
	@Qualifier("dataSourceOriginal")
	DataSource ds; // (org.springframework.jdbc.datasource.DriverManagerDataSource)
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	
	public List<EmpVO> selectAll() {
		String sql = "select * from employees order by employee_id";
		List<EmpVO> emplist = new ArrayList<>();

		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while (rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}

		return emplist;
	}
	
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO();
		emp.setCommission_pct(rs.getDouble("Commission_pct"));
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmail(rs.getString("Email"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setHire_date(rs.getDate("Hire_date"));
		emp.setJob_id(rs.getString("Job_id"));
		emp.setLast_name(rs.getString("Last_name"));
		emp.setManager_id(rs.getInt("Manager_id"));
		emp.setPhone_number(rs.getString("Phone_number"));
		emp.setSalary(rs.getDouble("Salary")); 

		return emp;
	}
}
