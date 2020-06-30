package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.StudentBean;
import edu.prj.dao.StudentDao;
import edu.prj.util.DbPub;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Long insert(StudentBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append("insert into Student(LoginName,LoginPwd,name,IsDisabled,RoomID)");
		sb.append("values (?,?,?,?,?)");

		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getName());
		paramsList.add(bean.getIsDisabled());
		paramsList.add(bean.getRoomID());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		if (num > 0) {
			// sql = "Select max(id) from Student ";
			sql = "SELECT LAST_INSERT_id()"; // 或者Select @@identity
			Long result = DbPub.queryScalarLong(conn, sql);
			if (result > 0) {
				bean.setStudentID(result);
				num = result;
			}
		}
		DbPub.close(conn);

		return num;
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" delete from Student ");
		sb.append(" where studentId = ?");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public Long update(StudentBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Update Student Set ");
		sb.append(" loginName = ?");
		sb.append(" ,loginPwd = ?");
		sb.append(" ,name = ? ");
		sb.append(" ,isDisabled = ? ");
		sb.append(" ,roomID = ? ");
		sb.append("    Where StudentID = ?");
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getName());
		paramsList.add(bean.getIsDisabled());		
		paramsList.add(bean.getRoomID());
		paramsList.add(bean.getStudentID());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);

		return num;
	}

	@Override
	public List<StudentBean> list() {
		// TODO Auto-generated method stub
		List<StudentBean> list = new ArrayList<StudentBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From Student ");
		sb.append(" Where 1=1 ");
		sb.append("  Order by StudentID ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			StudentBean bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);

		return list;
	}

	private StudentBean toBean(ResultSet rs) throws SQLException {
		StudentBean bean;
		bean = new StudentBean();
		bean.setStudentID(rs.getLong("studentId"));
		bean.setLoginName(rs.getString("loginName"));
		bean.setLoginPwd(rs.getString("loginPwd"));
		bean.setName(rs.getString("name"));
		bean.setIsDisabled(rs.getLong("isDisabled"));
		bean.setRoomID(rs.getLong("roomId"));
		return bean;
	}

	@Override
	public StudentBean load(Long id) {
		// TODO Auto-generated method stub
		StudentBean bean = null;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From Student");
		sb.append(" Where 1=1 ");
		sb.append("  And studentID=?");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {

			if (rs.next()) {
				bean = toBean(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DbPub.close(conn);
		return bean;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select count(1) From Student");
		sb.append(" Wherre 1=1 ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public StudentBean loadByName(String name) {
		// TODO Auto-generated method stub
		StudentBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From Student");
		sb.append(" Where 1=1 ");
		sb.append("  And loginName=?");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {

			if (rs.next()) {
				bean = toBean(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DbPub.close(conn);
		return bean;
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select count(1) From Student");
		sb.append(" Wherre 1=1 ");
		sb.append("  And loginName=?");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<StudentBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<StudentBean> list = new ArrayList<StudentBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select * From Student ");
		sb.append(" Where 1=1 ");
		sb.append(" 	and name like ?  ");
		sb.append("  Order by StudentID ");
		name = "%" + name + "%";
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			StudentBean bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);

		return list;

	}

}