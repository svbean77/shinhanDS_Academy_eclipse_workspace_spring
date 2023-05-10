package com.shinhan.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.util.OracleUtil;
import com.shinhan.vo.DeptVO;
import com.shinhan.vo.EmpVO;
import com.shinhan.vo.JobVO;

@Repository
public class CompanyDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	@Autowired // Autowired: 타입이 같으면 자동 주입
	@Qualifier("dataSourceOriginal") // Qualifier: 타입이 같은 bean이 여러 개일 때 해당 이름을 찾아 주입, 예전에 쓰던 코드 계속 쓰고 싶어서 넣음
	DataSource ds;

	public List<DeptVO> deptSelectAll() {
		List<DeptVO> deptList = new ArrayList<>();
		String sql = "select * from departments order by 1";
 
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDepartment_id(rs.getInt("Department_id"));
				dept.setDepartment_name(rs.getString("Department_name"));
				dept.setLocation_id(rs.getInt("Location_id"));
				dept.setManager_id(rs.getInt("Manager_id"));

				deptList.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}

		return deptList;
	}

	public List<JobVO> jobSelectAll() {
		List<JobVO> jobList = new ArrayList<>();
		String sql = "select * from jobs order by 1";
 
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				JobVO job = new JobVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));

				jobList.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}

		return jobList;
	}

	public List<EmpVO> managerSelectAll() {
		List<EmpVO> mList = new ArrayList<>();
		String sql = "select employee_id, first_name, last_name from employees "
				+ "where employee_id in (select distinct manager_id from employees)";
 
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployee_id(rs.getInt(1));
				emp.setFirst_name(rs.getString(2));
				emp.setLast_name(rs.getString(3));

				mList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}

		return mList;
	}
}
