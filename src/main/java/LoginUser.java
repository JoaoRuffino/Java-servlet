

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Authenticator.Authenticator;
import model.User;
import DAO.UserDao;
import DAO.UserDaoImpl;

@WebServlet("/user/login")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginUser() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
    	UserDao userdao = new UserDaoImpl();

        User user = new User();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        user.setEmail(email);
        user.setPassword(password);
        
        try {
			boolean verify = userdao.loginUser(user);
			if (verify) {
				String token = Authenticator.createToken(email);
				response.setContentType("application/json");
	            response.getWriter().write("{\"token\": \"" + token + "\"}");
		        response.setStatus(HttpServletResponse.SC_OK);
	       } 
			else {
		        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            out.print("{\"message\": \"Invalid credentials.\"}");
		    }			
		} catch (SQLException e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }         
    }
	


}
