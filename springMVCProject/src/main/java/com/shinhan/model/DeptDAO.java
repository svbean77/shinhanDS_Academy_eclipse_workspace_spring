package com.shinhan.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.shinhan.vo.DeptVO;
import com.shinhan.vo.EmpVO;

@Repository
public class DeptDAO {
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	Connection conn;
	int resultCount;
	@Autowired
	@Qualifier("dataSourceOriginal")
	DataSource ds;

	public List<DeptVO> deptList() {
		String sql = "select * from departments order by department_id desc";
		List<DeptVO> deptlist = new ArrayList<>();

		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDepartment_id(rs.getInt("Department_id"));
				dept.setDepartment_name(rs.getString("Department_name"));
				dept.setManager_id(rs.getInt("Manager_id"));
				dept.setLocation_id(rs.getInt("Location_id"));

				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}

		return deptlist;
	}

	public DeptVO selectById(int deptid) {
		String sql = "select * from departments where department_id=" + deptid;
		DeptVO dept = new DeptVO();

		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				dept.setDepartment_id(rs.getInt("Department_id"));
				dept.setDepartment_name(rs.getString("Department_name"));
				dept.setManager_id(rs.getInt("Manager_id"));
				dept.setLocation_id(rs.getInt("Location_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}

		return dept;
	}

	public int deptUpdate(DeptVO dept) {
		String sql = "update departments " + "set department_name=?, manager_id=?, location_id=? where department_id=?";

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, dept.getDepartment_name());
			int manager = dept.getManager_id();
			if (manager == 0)
				pst.setNull(2, Types.NULL);
			else
				pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
			pst.setInt(4, dept.getDepartment_id());
			resultCount = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount;
	}

	public int deptDelete(int deptid) {
		String sql = "delete from departments where department_id=?";

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			resultCount = pst.executeUpdate();

		} catch (SQLException e) {
			resultCount = -1;
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount;
	}

	public int deptInsert(DeptVO dept) {
		String sql = "insert into departments values(departments_seq.nextval, ?, ?, ?)";

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, dept.getDepartment_name());
			int manager = dept.getManager_id();
			if (manager == 0)
				pst.setNull(2, Types.NULL);
			else
				pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount;
	}
}
