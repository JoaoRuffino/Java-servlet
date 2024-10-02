package controller;
import model.User;
import db.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Consume {

	
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
				if(rs.getString("password").equals(user.getPassword())) {
					user.setUsername(rs.getString("username"));
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
}
