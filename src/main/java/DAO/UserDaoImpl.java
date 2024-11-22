package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import db.DBQuery;
import db.DBConnection;
import model.User;

public class UserDaoImpl implements UserDao {
	
	private DBQuery dbQuery;
	
	public UserDaoImpl() {
		this.dbQuery = new DBQuery("users", "user_id, username, email, cep", "user_id");
	}
	public List<User> getUser() throws SQLException {
		List<User> users = new ArrayList<>();
		try(ResultSet rs = dbQuery.select("")){
			while(rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setCep(rs.getString("cep"));
				users.add(user);
			}
		}
		return users;
	}
	

	public boolean loginUser(User user) throws SQLException, Exception {
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
	
	public boolean setUser(User user) throws SQLException, Exception{
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
	
	public boolean deleteUser(User user) throws SQLException, Exception{
		String[] values = new String[]{String.valueOf(user.getUser_id())};
        return dbQuery.delete(values) > 0;
	}
	public boolean updateUser(User user) throws SQLException, Exception{
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
