package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import entity.User;
import main.DBUtil;

public class UserDao {

	
	private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
	private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
	private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
	private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
	private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
	
	
	public String hashPassword(String password) {
		    return BCrypt.hashpw(password, BCrypt.gensalt());
		}
	
	
	public User create(User user) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, hashPassword(user.getPassword()));
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next()) {
				user.setId((long) rs.getInt(1));
			}
			return user;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}

	public User read(long userId) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(READ_USER_QUERY);
			preparedStatement.setLong(1, userId);
			User user = new User();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				user.setUsername(username);
				user.setEmail(email);
				user.setPassword(password);
				user.setId(userId);
			}
			
			return user;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		
	}
	
	public void update(User user) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER_QUERY);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, hashPassword(user.getPassword()));
			preparedStatement.setLong(4,  user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void delete(long userId) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER_QUERY);
			preparedStatement.setLong(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public List<User> findAll() {
		try(Connection conn = DBUtil.getConnection()) {
			List<User> users = new ArrayList<>();
			PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			
			return users;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
}
