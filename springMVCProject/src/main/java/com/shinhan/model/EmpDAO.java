package com.shinhan.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.util.OracleUtil;
import com.shinhan.vo.EmpVO;

@Repository // @Component + DAO
public class EmpDAO {
	@Autowired // 타입이 같으면 자동 주입: XML 파일에 bean으로 등록된 bean을 만들어 자동 주입)
	@Qualifier("dataSourceOriginal")
	DataSource ds;
	
	Connection conn; 
	Statement st;
	PreparedStatement pst; 
	CallableStatement cst; 
	ResultSet rs; 
	int resultCount;

	public EmpVO getSalary(int empid) { 
		String sql = "{call sp_salary(?,?,?)}";
		EmpVO emp = new EmpVO();

		try {
			conn = ds.getConnection();
			cst = conn.prepareCall(sql);
			cst.setInt(1, empid); 
			cst.registerOutParameter(2, Types.DOUBLE); 
			cst.registerOutParameter(3, Types.VARCHAR);
			cst.execute(); 
			
			emp.setSalary(cst.getDouble(2));
			emp.setFirst_name(cst.getString(3));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp;
	}

	public List<EmpVO> selectAll() {
		String sql = "select * from employees order by employee_id desc";
		List<EmpVO> emplist = new ArrayList<>();
 
		try {
			conn = ds.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(sql);
			while (rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}

		return emplist;
	}

	public EmpVO selectById(int empid) {
		String sql = "select * from employees where employee_id=" + empid;
		EmpVO emp = null;

		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}

		return emp;
	}

	public List<EmpVO> selectByDept(int deptid) {
		String sql = "select * from employees where department_id=" + deptid;
		List<EmpVO> emplist = new ArrayList<>();

		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}

		return emplist;
	}

	public List<EmpVO> selectByCondition(int deptid, String jobid, double salary) {
		String sql = "select * from employees " + "where department_id=? " + "and job_id=? " + "and salary>=?";
		List<EmpVO> emplist = new ArrayList<>();

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			pst.setString(2, jobid);
			pst.setDouble(3, salary);
			rs = pst.executeQuery();

			while (rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}

		return emplist;
	}

	public List<EmpVO> selectLAB() {
		String sql = "select employee_id, first_name, salary, employees.department_id "
				+ "from employees, (select department_id myDept, avg(salary) avgSal "
				+ "from employees group by department_id) "
				+ "where salary < avgSal "
				+ "and department_id=myDept";
		List<EmpVO> emplist = new ArrayList<>();
 
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				EmpVO emp = makeEmp2(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}

		return emplist;
	}

	public int empInsert(EmpVO emp) {
		String sql = "insert into employees values(seq_employee.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, emp.getFirst_name());
			pst.setString(2, emp.getLast_name());
			pst.setString(3, emp.getEmail());
			pst.setString(4, emp.getPhone_number());
			pst.setDate(5, emp.getHire_date());
			pst.setString(6, emp.getJob_id());
			pst.setDouble(7, emp.getSalary());
			pst.setDouble(8, emp.getCommission_pct());
			pst.setInt(9, emp.getManager_id());
			pst.setInt(10, emp.getDepartment_id());
			resultCount = pst.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount;
	}

	public int empUpdate(EmpVO emp) {
		String sql = "update employees "
				+ "set email=?, department_id=?, job_id=?, salary=?, "
				+ "hire_date=?, manager_id=?, first_name=?, last_name=? where employee_id=?";
 
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, emp.getEmail());
			pst.setInt(2, emp.getDepartment_id());
			pst.setString(3, emp.getJob_id());
			pst.setDouble(4, emp.getSalary());
			pst.setDate(5, emp.getHire_date());
			pst.setInt(6, emp.getManager_id());
			pst.setString(7, emp.getFirst_name());
			pst.setString(8, emp.getLast_name());
			pst.setInt(9, emp.getEmployee_id());
			resultCount = pst.executeUpdate(); 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount;
	}

	public int empDelete(int empid) {
		String sql = "delete from employees where employee_id=?";
 
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, empid);
			resultCount = pst.executeUpdate(); 

		} catch (SQLException e) {
			resultCount = -1;
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount;
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

	private EmpVO makeEmp2(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO();
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setSalary(rs.getDouble("Salary"));

		return emp;
	}
}
