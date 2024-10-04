package controller;
import model.User;
import db.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
public class ControllerUser {

	
	public List<User> consumeUsers() throws SQLException, Exception {
		DBConnection conn = new DBConnection();
		String query = "select * from users";
		List<User> users = new ArrayList<>();
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			User user = new User();
			user.setUser_id(rs.getInt("user_id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			users.add(user);
		}
		
		
		if (rs != null) rs.close();
        if (statement != null) statement.close();
        
        return users;
	}
	

	public boolean userLoginDB(User user) throws SQLException, Exception {
		DBConnection conn = new DBConnection();
		String query = "select * from users where email =?";
		
		
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		
		statement.setString(1, user.getEmail());
		ResultSet rs = statement.executeQuery();
		
		if(rs.next()) {
			if(rs.getString("email") != null) {
				
				if(BCrypt.checkpw(user.getPassword(), rs.getString("password"))) {
					user.setUsername(rs.getString("username"));
					user.setUser_id(rs.getInt("user_id"));
					user.setPassword(rs.getString("password"));
					return true;
				}
			}
		}else {
			return false;
		}
		if (rs != null) rs.close();
        if (statement != null) statement.close();
        return false;
	}
	
	public boolean userRegister(User user) throws SQLException, Exception{
		DBConnection conn = new DBConnection();
		String query = "insert into users(username, password, email, cep) values (?, ?, ?, ?)";
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getCep());
		int row = statement.executeUpdate();
		if(row > 0) {
	        if (statement != null) statement.close();
			return true;
		}
		return false;
	}
	
	public boolean userDelete(User user) throws SQLException, Exception{
		DBConnection conn = new DBConnection();
		String query = "delete from users where user_id = ?";
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		statement.setInt(1, user.getUser_id());
		int row = statement.executeUpdate();
		if(row > 0) {
	        if (statement != null) statement.close();
			return true;
		}
		return false;
	}
	public boolean userUpdate(User user) throws SQLException, Exception{
		DBConnection conn = new DBConnection();
		
		String query = "update users set email = ?, username = ?, cep = ? where user_id = ?";
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getUsername());
		statement.setString(3, user.getCep());
		statement.setInt(4, user.getUser_id());


		int row = statement.executeUpdate();
		if(row > 0) {
	        if (statement != null) statement.close();
			return true;
		}
		return false;
	}
	
}
