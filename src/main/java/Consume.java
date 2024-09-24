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
			user.setLastname(rs.getString("lastname"));
			users.add(user);
		}
		
		
		if (rs != null) rs.close();
        if (statement != null) statement.close();
        
        return users;
	}
		
}
