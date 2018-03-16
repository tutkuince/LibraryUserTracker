package com.usertracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usertracker.model.User;
import com.usertracker.util.JDBCUtil;

public class UserDAO implements GenericDAO<User> {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private final String GET_ALL_USER = "select * from user";

	@Override
	public void insert(User entity) {
		conn = new JDBCUtil().getConnection();

		try {
			pstmt = conn.prepareStatement("insert into user(first_name, last_name, email) values(?, ?, ?)");

			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setString(3, entity.getEmail());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	@Override
	public void delete(int id) {
		conn = new JDBCUtil().getConnection();
		
		try {
			pstmt = conn.prepareStatement("delete from user where id=?");
			pstmt.setInt(1, id);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	@Override
	public void update(User entity) {
		conn = new JDBCUtil().getConnection();
		
		try {
			pstmt = conn.prepareStatement("update user set first_name=?, last_name=?, email=? where id=?");
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setString(3, entity.getEmail());
			pstmt.setInt(4, entity.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	@Override
	public User getById(int id) {
		User user = null;
		conn = new JDBCUtil().getConnection();

		try {
			pstmt = conn.prepareStatement("select * from user where id= ?");
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				user = new User(id, firstName, lastName, email);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<>();
		conn = new JDBCUtil().getConnection();

		try {
			pstmt = conn.prepareStatement(GET_ALL_USER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				User tempUser = new User(id, firstName, lastName, email);
				userList.add(tempUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return userList;
	}

	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (conn != null || pstmt != null || rs != null) {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void close(Connection conn, PreparedStatement pstmt) {
		if (conn != null || pstmt != null) {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
