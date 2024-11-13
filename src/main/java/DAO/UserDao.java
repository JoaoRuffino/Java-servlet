package DAO;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserDao {
	public List<User> getUser() throws SQLException, Exception;
	public boolean loginUser(User user) throws SQLException, Exception;
	public boolean setUser(User user) throws SQLException, Exception;
	public boolean deleteUser(User user) throws SQLException, Exception;
	public boolean updateUser(User user) throws SQLException, Exception;
}
