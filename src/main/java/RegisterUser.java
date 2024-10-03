

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;


import controller.ControllerUser;
import model.User;


@WebServlet("/user/register")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegisterUser() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ControllerUser controll = new ControllerUser();
        
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hashSenha = BCrypt.hashpw(password, BCrypt.gensalt());
        String cep = request.getParameter("cep");
        
        User user = new User();
        user.setCep(cep);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashSenha);
        
        try {
        	
        	if(controll.userRegister(user)) {
		        response.setStatus(HttpServletResponse.SC_OK);
	            out.print("Create User successful");		    
        	}else {
		        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            out.print("Error creating user");		    

        	}
        }catch (SQLException e) {
            e.printStackTrace();
            out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
	}
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("DELETE request received");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("PUT request received");
    }

}
